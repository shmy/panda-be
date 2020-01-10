#!groovy
node {

  stage('Build') {
    checkout scm
    sh './gradlew clean build'
  }

  stage('Sonar Scan') {
    sh './gradlew sonarqube -x test'
  }

  stage('Deploy') {
    docker.build('panda-be')
    docker.withRegistry('https://955095959256.dkr.ecr.cn-northwest-1.amazonaws.com.cn', 'ecr:cn-northwest-1:panda-ecr') {
      docker.image('panda-be').push('latest')
    }

    dir('config/k8s') {
      def exists = fileExists './kubectl'
      if (!exists) {
        sh 'curl -LO https://s3.cn-north-1.amazonaws.com.cn/kops-bjs/fileRepository/kubernetes-release/release/v1.15.5/bin/linux/amd64/kubectl'
        sh 'chmod +x ./kubectl'
      }

      sh './kubectl delete deployment/panda-be-deployment --ignore-not-found=true'
      sh './kubectl delete service/panda-be-service --ignore-not-found=true'
      sh 'sleep 90'
      sh './kubectl create -f panda-be.yaml'
    }

  }

}

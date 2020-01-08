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
      docker.image('alpine').inside('-u 0:0') {
        sh 'apk add curl'
        sh 'curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl'
        sh 'chmod +x ./kubectl'
        sh './kubectl --kubeconfig=k8s-config delete deployment/panda-be-deployment --ignore-not-found=true'
        sh './kubectl --kubeconfig=k8s-config delete service/panda-be-service --ignore-not-found=true'
        sh 'sleep 60'
        sh './kubectl --kubeconfig=k8s-config create -f panda-be.yaml'
      }
    }
  }

}

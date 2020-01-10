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
      docker.image('panda-be').push('${BUILD_NUMBER}')
    }

    dir('config/k8s') {
      def exists = fileExists './kubectl'
      if (!exists) {
        sh 'curl -LO https://s3.cn-north-1.amazonaws.com.cn/kops-bjs/fileRepository/kubernetes-release/release/v1.15.5/bin/linux/amd64/kubectl'
        sh 'chmod +x ./kubectl'
      }

      sh 'eval "cat <<EOF
          $(< panda-be-template.yaml)
          EOF
          "  > panda-be.yaml'

      sh 'cat panda-be.yaml'
      sh 'BUILD_NUM=${BUILD_NUMBER} && ./kubectl apply -f panda-be.yaml'
    }

  }

}

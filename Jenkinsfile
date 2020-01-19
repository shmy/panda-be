#!groovy
def label = "jenkins-slave"
node(label) {

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
      def exists = fileExists('./kubectl')
      println  exists
      if (!exists) {
        sh 'curl -LO https://s3.cn-north-1.amazonaws.com.cn/kops-bjs/fileRepository/kubernetes-release/release/v1.15.5/bin/linux/amd64/kubectl'
        sh 'chmod +x ./kubectl'
      }

      sh 'sed -e "s#{BUILD_NUM}#${BUILD_NUMBER}#g" panda-be.yaml |./kubectl apply -f -'
    }

  }

}

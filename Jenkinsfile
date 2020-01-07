pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }

    stage('Sonar Scan') {
      steps {
        withSonarQubeEnv('My SonarQube Server') {
          sh './gradlew sonarqube -x test'
        }
      }

      post {
        always {
          timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
          }
        }
      }
    }


    stage('Deploy') {
      steps {
        script {
          docker.build('panda-be')
          docker.withRegistry('https://955095959256.dkr.ecr.cn-northwest-1.amazonaws.com.cn', 'ecr:cn-northwest-1:panda-ecr') {
            docker.image('panda-be').push('latest')
          }
        }
      }
      post {
        success {
          sh 'docker images -q -f dangling=true | xargs --no-run-if-empty docker rmi'
        }
      }
    }


  }
}
pipeline {
  agent {
    docker {
      image 'gradle:jdk11'
    }

  }
  stages {
    stage('Assemble') {
      steps {
        sh './gradlew clean assemble'
      }
    }

    stage('Build') {
      steps {
        sh './gradlew build -x test'
      }
    }

    stage('Test') {
      steps {
        sh './gradlew test'
      }
    }

    stage('Push Image') {
      steps {
        script {
          docker.withRegistry('955095959256.dkr.ecr.cn-northwest-1.amazonaws.com.cn', 'ecr:cn-northwest-1:panda-ecr') {
            docker.image('panda-be').push('latest')
          }
        }

      }
    }

  }
}
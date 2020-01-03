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

  }
}
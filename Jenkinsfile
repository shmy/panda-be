#!groovy
node {
  stage ("Checkout") {
    checkout scm
  }

  stage('Build') {
    sh './gradlew clean build'
  }

  stage('Deploy') {
    docker.image('bitnami/kubectl').inside {
      sh 'kubectl --kubeconfig=k8s-config delete deployment/panda-be-deployment'
      sh 'kubectl --kubeconfig=k8s-config delete service/panda-be-service'
      sh 'kubectl --kubeconfig=k8s-config create -f panda-be.yaml'
    }
  }
}

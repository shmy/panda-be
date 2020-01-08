#!groovy
node {
  stage ("Checkout") {
    checkout scm
  }

  stage('Build') {
    sh './gradlew clean build'
  }

  stage('Deploy') {
      sh 'curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl'
      sh 'chmod +x ./kubectl'
      sh './kubectl --kubeconfig=k8s-config delete deployment/panda-be-deployment'
      sh './kubectl --kubeconfig=k8s-config delete service/panda-be-service'
      sh 'sleep 30'
      sh './kubectl --kubeconfig=k8s-config create -f panda-be.yaml'
  }
}

def call(String branchName, String registryUrl) {
  def port = branchName == 'main' ? '8083' : '8084'
  def envName = branchName == 'main' ? 'prod' : 'uat'
  
  withCredentials([usernamePassword(
    credentialsId: 'nexus-credentials',
    usernameVariable: 'USER',
    passwordVariable: 'PASS'
  )]) {
    sh """
    echo \$PASS | docker login ${registryUrl} -u \$USER --password-stdin
    docker rm -f petclinic-${envName} || true
    docker pull ${registryUrl}/petclinic:latest
    docker run -d -p ${port}:8080 --name petclinic-${envName} ${registryUrl}/petclinic:latest
    """
  }
}

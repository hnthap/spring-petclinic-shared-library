def call(String registryUrl) {
  sh './mvnw package -DskipTests'
  sh "docker build -t ${registryUrl}/petclinic:latest ."
  withCredentials([usernamePassword(
    credentialsId: 'nexus-credentials',
    usernameVariable: 'USER',
    passwordVariable: 'PASS'
  )]) {
    sh "echo \$PASS | docker login ${registryUrl} -u \$USER --password-stdin"
    sh "docker push ${registryUrl}/petclinic:latest"
  }
}

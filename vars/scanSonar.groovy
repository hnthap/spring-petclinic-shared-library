def call(String hostUrl) {
  withCredentials([string(
    credentialsId: 'sonar-token',
    variable: 'SONAR_TOKEN'
  )]) {
    sh "./mvnw sonar:sonar -Dsonar.host.url=${hostUrl} -Dsonar.login=\$SONAR_TOKEN"
  }
}

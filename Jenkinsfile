pipeline {
    agent any
    stages {
        stage('Build JAR') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t=uzairsiddiqui38/selenium:latest ."
            }
        }
        stage('Pushing Image to Docker Hub') {
            environment {
                DOCKER_HUB = credentials('docker_hub_credentials')
            }
            steps {
                // Login just before pushing to ensure fresh session
                bat 'echo %DOCKER_HUB_PSW% | docker login -u %DOCKER_HUB_USR% --password-stdin'
                retry(3) {
                    bat "docker push uzairsiddiqui38/selenium:latest"
                }
                bat "docker tag uzairsiddiqui38/selenium:latest uzairsiddiqui38/selenium:${env.BUILD_NUMBER}"
                retry(3) {
                    bat "docker push uzairsiddiqui38/selenium:${env.BUILD_NUMBER}"
                }
            }
        }
    }
    post {
        always {
            bat "docker logout"
        }
    }
}
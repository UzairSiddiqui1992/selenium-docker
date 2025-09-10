pipeline{
agent any
stages{
    stage('Build JAR'){
        steps{
            bat "mvn clean package -DskipTests"
        }
    }
    stage('Build Docker Image'){
        steps{
            bat "docker build -t=uzairsiddiqui38/selenium ."
        }
    }
    stage('Pushing Image to Docker Hub'){
        steps{
            bat "docker push uzairsiddiqui38/selenium"
        }
    }
}
}
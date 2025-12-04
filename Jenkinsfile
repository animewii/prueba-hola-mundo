pipeline {
    agent any

    tools {
        maven 'Maven'      // Cambia al nombre de Maven configurado en Jenkins si es distinto
        jdk 'JDK17'        // Cambia al nombre de tu JDK en Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/animewii/prueba-hola-mundo.git'
            }
        }

        stage('Build & SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn clean verify sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}

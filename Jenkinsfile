pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk   'JDK17'
    }
    
    environment {
        REPO_URL = 'https://github.com/animewii/prueba-hola-mundo.git'
        BRANCH   = 'main'
        SONARQ   = 'SonarQube'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: BRANCH, url: REPO_URL
            }
        }

        stage('Build & SonarQube') {
            steps {
                withSonarQubeEnv(SONARQ) {
                    sh 'mvn clean verify sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 15, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        echo "Quality Gate status: ${qg.status}"
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
}


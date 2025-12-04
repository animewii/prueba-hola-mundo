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

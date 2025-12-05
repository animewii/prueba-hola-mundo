pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
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

        stage('Package') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy to Prod') {
            steps {
                deploy adapters: [
                    tomcat9(
                        credentialsId: 'tomcat-test',
                        path: '',
                        url: 'http://localhost:8083'
                    )
                ],
                contextPath: '/hola-mundo',
                war: 'target/*.war'
            }
        }
    }

    post {
        success {
            emailext(
                subject: "OK: ${env.JOB_NAME} #${env.BUILD_NUMBER} desplegado en PROD",
                body: """El pipeline se ejecutó correctamente.

Job: ${env.JOB_NAME}
Build: ${env.BUILD_NUMBER}
Estado: SUCCESS
Despliegue realizado en Tomcat PROD (8083).

Saludos,
Jenkins
""",
                recipientProviders: [developers(), requestor()]
            )
        }

        failure {
            emailext(
                subject: "FALLO: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """El pipeline ha fallado.

Job: ${env.JOB_NAME}
Build: ${env.BUILD_NUMBER}
Revisa la consola de Jenkins y el análisis de SonarQube.

Saludos,
Jenkins
""",
                recipientProviders: [developers(), requestor()]
            )
        }
    }
}


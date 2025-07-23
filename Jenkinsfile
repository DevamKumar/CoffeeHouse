pipeline {
    agent any

    environment {
        REGISTRY = 'devamkumar/coffeehouse'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${REGISTRY}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Push to Registry') {
            environment {
                DOCKER_USER = credentials('docker-credentials-id')
                DOCKER_PASS = credentials('docker-credentials-id')
            }
            steps {
                script {
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    sh "docker push ${REGISTRY}:${IMAGE_TAG}"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh 'kubectl apply -f kubernetesFiles/'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Deployment failed!'
        }
    }
}

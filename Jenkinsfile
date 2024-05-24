pipeline {
    agent any
    stages {
        stage('Build Tournament Manager') {
            steps {
                build job: 'store.tournamentmanager', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }      
        stage('Build Image') {
            steps {
                script {
                    tournamentmanager = docker.build("enzoq2202/tournamentmanager:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        tournamentmanager.push("${env.BUILD_ID}")
                        tournamentmanager.push("latest")
                    }
                }
            }
        }
        stage('Deploy on k8s') {
            steps {
                withCredentials([ string(credentialsId: 'minikube_credentials', variable: 'api_token') ]) {
                    sh 'kubectl --token $api_token --server https://host.docker.internal:62598  --insecure-skip-tls-verify=true apply -f ./k8s/deployment.yaml'
                    sh 'kubectl --token $api_token --server https://host.docker.internal:62598  --insecure-skip-tls-verify=true apply -f ./k8s/service.yaml'
                }
            }
        }
    }
}

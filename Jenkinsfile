pipeline {
    agent any

    stages {
        stage('Build') {
            agent {
              	docker {
               		image 'maven:3-alpine'
              }
            }
            steps {
                echo 'Building...'
                sh 'mvn --version'
				sh 'mvn clean package'
            }
        }

        stage('Test') {
            agent {
              	docker {
               		image 'maven:3-alpine'
              }
            }
            steps {
                echo 'Testing...'
                sh 'mvn --version'
                sh 'mvn clean test'
            }
        }

        stage('QualityTest') {
        	agent {
              	docker {
               		image 'maven:3-alpine'
              }
            }
        	steps {
                echo 'Quality test...'
        	    sh '(mvn sonar:sonar -Dsonar.projectKey=JolieKimy_TheWeb -Dsonar.organization=joliekimy-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=3e09b122abbed08332a651eed10bf43130ac3286)'
        	}
        }

        stage ('IntegrationTest') {
            agent {
                docker {
                    image 'lucienmoor/katalon-for-jenkins:latest'
				    args '-p 8888:8080'
                }
            }
            steps {
                echo 'Integration test...'
                sh 'java -jar ./target/TheWeb-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
                sh 'sleep 30'
                sh 'chmod +x ./runTest.sh'
                sh './runTest.sh'

                cleanWS()
            }
        }
    }

    post {
        always {
            echo 'always clean up'
            deleteDir()
        }
    }
}
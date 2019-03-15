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
				sh 'mvn clean test'
            }
        }
        stage('QualityTest')
        {
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
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
pipeline {
	agent any
	tools {
		Maven 'Maven 3.3.9'
	}
	stages {
		stage('Build') {
			steps {
				sh 'mvn clean install'
			}
		}
		stage('Build Docker Image') {
			steps {
				echo "Would build docker image here"
			}
		}
	}
}

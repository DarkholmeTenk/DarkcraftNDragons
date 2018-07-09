pipeline {
	agent any
	tools {
		maven 'maven'
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

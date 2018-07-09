pipeline {
	agent any
	tools {
		maven 'Maven'
	}
	stages {
		stage('Build') {
			steps {
				sh 'mvn clean install'
			}
		}
		stage('Build Docker Image') {
			steps {
				sh 'ls -l target/'
				sh 'docker build --build-arg JAR_FILE=${WORKSPACE}/target/*.jar -t dnd/dnd:latest .'
			}
		}
	}
}

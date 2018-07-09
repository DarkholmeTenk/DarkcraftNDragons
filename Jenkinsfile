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
				sh 'mvn docker:build'
			}
		}
	}
}

pipeline {
	agent any
	tools {
		maven 'Maven'
		docker 'Docker'
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

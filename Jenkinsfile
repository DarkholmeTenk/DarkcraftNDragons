pipeline {
	agent any
	tools {
		maven 'Maven'
		org.jenkinsci.plugins.docker.commons.tools.DockerTool 'Docker'
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

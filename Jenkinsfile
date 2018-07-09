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
			withMaven(
				maven: 'Maven'
				mavenSettingsFilePath: 'settings.xml'
			) {
				sh 'mvn package docker:build'
			}
		}
	}
}

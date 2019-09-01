pipeline {
    agent any
    options {
	skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
		sh 'mvn clean -DskipTests'
            }
	}
        stage('Test') {
            steps {
		sh 'cd backend/ && mvn test && cd ..'
            }
	}
        stage('Deploy') {
            steps {
		sh 'mvn clean install -DskipTests'
            }
	}
    }
}
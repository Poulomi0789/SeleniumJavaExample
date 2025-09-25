pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/Poulomi0789/SeleniumJavaExample.git'
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
    }

    triggers {
        pollSCM('H/5 * * * *')  // Poll GitHub every 5 minutes
    }

    options {
        skipStagesAfterUnstable()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {

        stage('Checkout Code') {
            steps {
                git url: "${GIT_REPO}", branch: 'main'
            }
        }

        stage('Run Selenium Tests') {
            when {
                branch 'main' // Only run when changes are pushed to 'main' (i.e., after PR merge)
            }
            steps {
                echo 'Running Selenium tests from main branch...'
                sh 'mvn clean install'
            }
        }
    }

    post {
        always {
            echo 'Publishing test results...'
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Build and tests passed ✅'
        }

        failure {
            echo 'Build or tests failed ❌'
        }
    }
}


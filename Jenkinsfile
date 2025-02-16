pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk' // Java 17
        PATH = "$JAVA_HOME/bin:$PATH"
    }

    stages {
        stage('Compile') {
            steps {
                script {
                    try {
                        // Compile the Java files
                        sh 'javac -d out src/*.java'
                    } catch (e) {
                        // In case of failure, mark the build as unstable
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        // Run the tests using JUnit
                        sh 'java -cp "out:libs/*" org.junit.platform.console.ConsoleLauncher --scan-classpath'
                    } catch (e) {
                        // In case of test failure, mark the build as unstable
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }

        stage('Archive Build') {
            steps {
                // Archive the compiled Java files as build artifacts
                archiveArtifacts artifacts: 'out/**/*.class', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            // Clean up or any actions to be taken after the build
            echo 'Pipeline finished!'
        }

        success {
            echo 'Build and tests succeeded.'
        }

        failure {
            echo 'Build or tests failed.'
        }
    }
}

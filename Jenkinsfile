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
                        // test 2
                        // Compile the Java files in the root directory (Main.java)
                        sh 'javac -d out Main.java'
                    } catch (e) {
                        // In case of failure, mark the build as unstable
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
            // Report success to GitHub Checks
            githubChecks(name: 'Build', description: 'Build completed successfully', state: 'success')
        }

        failure {
            echo 'Build or tests failed.'
            // Report failure to GitHub Checks
            githubChecks(name: 'Build', description: 'Build failed', state: 'failure')
        }
    }
}

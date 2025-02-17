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
                    // Start the check
                    publishChecks(
                        name: 'Build',
                        title: 'Build Stage', 
                        summary: 'Building the project...', 
                        status: 'IN_PROGRESS',
                        detailsURL: "${env.BUILD_URL}",
                    )
                    try {
                        // Compile the Java files in the root directory (Main.java)
                        sh 'javac -d out Main.java'

                        // Check succeeded
                        publishChecks(
                            name: 'Build',
                            title: 'Build Stage', 
                            summary: 'Build success!', 
                            status: 'COMPLETED',
                            conclusion: 'SUCCESS',
                            detailsURL: "${env.BUILD_URL}",
                        )
                    } catch (e) {
                        // Check failed
                        publishChecks(
                            name: 'Build',
                            title: 'Build Stage', 
                            summary: 'Build failed :(', 
                            status: 'COMPLETED',
                            conclusion: 'FAILURE',
                            detailsURL: "${env.BUILD_URL}",
                        )
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }

        stage('Dummy Stage') {
            steps {
                script {
                    publishChecks(
                        name: 'Dummy Check',
                        title: 'Dummy Check', 
                        summary: 'Dummy Check', 
                        status: 'IN_PROGRESS',
                        detailsURL: "${env.BUILD_URL}",
                    )
                    publishChecks(
                        name: 'Dummy Check',
                        title: 'Dummy Check', 
                        summary: 'Dummy Check', 
                        status: 'COMPLETED',
                        conclusion: 'SUCCESS',
                        detailsURL: "${env.BUILD_URL}",
                    )
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
        }

        failure {
            echo 'Build or tests failed.'
            // Report failure to GitHub Checks
        }
    }
}

package ru.nsu.voronova.gradle

import org.gradle.tooling.GradleConnector
import java.io.File

class GradleManager(project: String) {
    private val testReport = "$project/build/reports/tests/test/index.html"
    private val documentation = "$project/build/docs/javadoc/index.html"
    private val gradleConnector = GradleConnector.newConnector().forProjectDirectory(File(project))

    fun build(): BuildReport {
        val projectConnection = gradleConnector.useGradleVersion("7.3").connect()
        val buildLauncher = projectConnection.newBuild().forTasks("test", "javadoc")
        return try {
            buildLauncher.run()
            BuildReport(true, testReport, documentation)
        } catch (exception: Exception) {
            BuildReport(false)
        } finally {
            projectConnection.close()
        }
    }
}
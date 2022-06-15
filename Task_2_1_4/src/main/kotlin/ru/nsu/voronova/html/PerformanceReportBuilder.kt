package ru.nsu.voronova.html

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import org.w3c.dom.Element
import ru.nsu.voronova.tables.PerformanceTable
import ru.nsu.voronova.file.FileManager

class PerformanceReportBuilder(private val performanceTables: List<PerformanceTable>, directoryPath: String) :
    ReportBuilder(directoryPath, "performance.html") {
    private val fileManager = FileManager()
    private fun createDiv(identifier: String): Element {
        val div = document.create.div {
            id = identifier
            h2 {
                style = "text-align: center"
                +"$identifier group academic performance"
            }
        }
        document.getElementById("main").appendChild(div)
        return div
    }

    private fun getDiv(group: String): Element {
        return document.getElementById(group) ?: createDiv(group)
    }

    private fun drawBuildReport(successful: Boolean, tr: TR) {
        if (successful) {
            tr.td {
                style = "color: green"
                +"Successful"
            }
        } else {
            tr.td {
                style = "color: red"
                +"Failed"
            }
        }
    }

    private fun drawTestReport(testReportPath: String?, tr: TR) {
        val fileContent = if (testReportPath != null) fileManager.readFile(testReportPath) else null
        val regex = "<div class=\"percent\">(.*?)%".toRegex()
        val passedTestsPercent =
            if (fileContent !== null) regex.find(fileContent)?.groupValues?.get(1)?.toInt() else null
        tr.td {
            if ((passedTestsPercent == null) || (passedTestsPercent != 100)) {
                a(testReportPath) {
                    style = "color: red"
                    href = ".$testReportPath"
                    +"Failed"
                }
            } else {
                a {
                    style = "color: green"
                    href = ".$testReportPath"
                    +"Passed"
                }
            }
        }
    }

    private fun drawDocumentationReport(documentationPath: String?, tr: TR) {
        if (documentationPath != null) {
            tr.td {
                a {
                    style = "color: blue"
                    href = ".$documentationPath"
                    +"See"
                }
            }
        } else {
            tr.td {
                style = "color: grey"
                +"Missing"
            }
        }
    }

    private fun drawPerformanceTable(performanceTable: PerformanceTable) {
        val groupDiv = getDiv(performanceTable.title)
        groupDiv.append {
            table {
                performanceTable.data.map { data ->
                    thead {
                        tr {
                            th {
                                style = "background: white"
                                colSpan = "5"
                                +data.key
                            }
                        }
                        tr {
                            th { +"Student" }
                            th { +"Build" }
                            th { +"Tests" }
                            th { +"Documentation" }
                            th { +"Points" }
                        }
                    }
                    tbody {
                        data.value.map { buildReport ->
                            tr {
                                td { +buildReport.key }
                                drawBuildReport(buildReport.value.successful, this)
                                drawTestReport(buildReport.value.testReport, this)
                                drawDocumentationReport(buildReport.value.documentation, this)
                                td { if (!buildReport.value.successful || buildReport.value.documentation == null) +"0.0" else +"1.0" }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun buildHTML() {
        performanceTables.map { performanceTable -> drawPerformanceTable(performanceTable) }
    }
}
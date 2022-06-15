package ru.nsu.voronova.html

import kotlinx.html.*
import kotlinx.html.dom.*
import org.w3c.dom.Element
import ru.nsu.voronova.tables.AttendanceTable

class AttendanceReportBuilder(private val attendanceTables: List<AttendanceTable>, directoryPath: String) :
    ReportBuilder(directoryPath, "attendance.html") {
    private fun createDiv(identifier: String): Element {
        val div = document.create.div {
            id = identifier
            h2 {
                style = "text-align: center"
                +"$identifier group attendance"
            }
        }
        document.getElementById("main").appendChild(div)
        return div
    }

    private fun getDiv(group: String): Element {
        return document.getElementById(group) ?: createDiv(group)
    }

    private fun drawAttendanceTable(attendanceTable: AttendanceTable) {
        val groupDiv = getDiv(attendanceTable.title)
        groupDiv.append {
            table {
                thead {
                    tr { attendanceTable.headers.map { header -> th { +header } } }
                }
                tbody {
                    attendanceTable.rows.map { row ->
                        tr { row.map { data -> td { +data } } }
                    }
                }
            }
        }
    }

    override fun buildHTML() {
        attendanceTables.map { attendanceTable -> drawAttendanceTable(attendanceTable) }
    }
}
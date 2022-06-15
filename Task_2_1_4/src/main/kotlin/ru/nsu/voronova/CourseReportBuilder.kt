package ru.nsu.voronova

import ru.nsu.voronova.tables.AttendanceTable
import ru.nsu.voronova.tables.PerformanceTable
import ru.nsu.voronova.dsl.model.*
import ru.nsu.voronova.file.FileManager
import ru.nsu.voronova.git.GitManager
import ru.nsu.voronova.gradle.BuildReport
import ru.nsu.voronova.gradle.GradleManager
import ru.nsu.voronova.html.AttendanceReportBuilder
import ru.nsu.voronova.html.PerformanceReportBuilder
import ru.nsu.voronova.script.ScriptRunner
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


fun main() {
    val courseReportBuilder = CourseReportBuilder()
    courseReportBuilder.build()
}

class CourseReportBuilder {
    private val gitDirectoryName = "repositories"
    private val reportDirectoryName = "report"
    private val fileName = "configuration.txt"
    private val importDSL = "import ru.nsu.voronova.dsl.builder.configuration\n"
    private val fileManager = FileManager()

    private fun buildConfiguration(): Configuration {
        return ScriptRunner().run(importDSL + fileManager.readFile(fileName))
    }

    private fun checkAttendance(group: String, student: String, lesson: Lesson): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = lesson.date
        calendar.add(Calendar.DATE, 7)
        val gitManager = GitManager(fileManager.getFilePath(gitDirectoryName, group))
        return student in lesson.attendance || gitManager.checkCommit(student, lesson.date, calendar.time)
    }

    private fun buildPerformanceTable(tasks: List<Task>, group: Group): PerformanceTable {
        val tableTitle = group.name
        val data = HashMap<String, Map<String, BuildReport>>()
        tasks.forEach { task ->
            run {
                val row = HashMap<String, BuildReport>()
                data[task.name] = row
                group.students.map { student ->
                    val gradleManager =
                        GradleManager("./$gitDirectoryName/${group.name}/${student.surname}/${task.name}")
                    val buildReport = gradleManager.build()
                    row[student.surname] = buildReport
                }
            }
        }
        return PerformanceTable(tableTitle, data)
    }

    private fun buildAttendanceTable(group: Group): AttendanceTable {
        val tableTitle = group.name
        val lessonsNumber = group.lessons.size
        val headers = ArrayList<String>()
        headers.add("Student")
        headers.addAll(group.lessons.map { lesson -> SimpleDateFormat("dd/MM/yyyy").format(lesson.date) })
        headers.add("Total")
        val rows = ArrayList<List<String>>()
        group.students.forEach { student ->
            run {
                val row = ArrayList<String>()
                row.add("${student.name} ${student.surname}")
                row.addAll(group.lessons.map { lesson ->
                    if (checkAttendance(
                            group.name,
                            student.surname,
                            lesson
                        )
                    ) "+" else "-"
                })
                row.add(
                    "${
                        group.lessons.foldRight(0) { lesson, sum ->
                            sum + if (checkAttendance(
                                    group.name,
                                    student.surname,
                                    lesson
                                )
                            ) 1 else 0
                        }
                    }/$lessonsNumber"
                )
                rows.add(row)
            }
        }
        return AttendanceTable(tableTitle, headers, rows)
    }

    private fun buildRepositories(group: Group) {
        val gitManager = GitManager(fileManager.getFilePath(gitDirectoryName, group.name))
        group.students.map { student -> gitManager.cloneRepository(student.repository, student.surname) }
    }

    fun build() {
        val configuration = buildConfiguration()
        fileManager.createDirectory(gitDirectoryName)
        configuration.groups.map { group -> buildRepositories(group) }
        val performanceTableBuilder = PerformanceReportBuilder(configuration.groups.map { group ->
            buildPerformanceTable(
                configuration.tasks,
                group
            )
        }, reportDirectoryName)
        performanceTableBuilder.build()
        val attendanceTableBuilder = AttendanceReportBuilder(
            configuration.groups.map { group -> buildAttendanceTable(group) },
            reportDirectoryName
        )
        attendanceTableBuilder.build()
    }
}
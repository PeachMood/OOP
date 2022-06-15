package ru.nsu.voronova.tables

import ru.nsu.voronova.gradle.BuildReport

typealias Student = String
typealias Task = String
data class PerformanceTable(val title: String, val data: Map<Task, Map<Student, BuildReport>>)

package ru.nsu.voronova.dsl.model

import java.net.URL

data class Student(
    val nickname: String,
    val name: String,
    val surname: String,
    val repository: URL,
    val assignedTasks: List<Int>)


package ru.nsu.voronova.dsl.model

data class Student(
    val nickname: String,
    val name: String,
    val surname: String,
    val repository: String,
    val assignedTasks: List<Int>
)


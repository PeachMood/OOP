package ru.nsu.voronova.dsl.model

data class Group(val name: String, val students: List<Student>, val lessons: List<Lesson>)

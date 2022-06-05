package ru.nsu.voronova.model

import java.util.*

data class Lesson(val date: Date, val attendance: MutableList<Student>)
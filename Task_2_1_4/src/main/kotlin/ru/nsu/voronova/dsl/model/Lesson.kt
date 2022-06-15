package ru.nsu.voronova.dsl.model

import java.util.*

data class Lesson(val date: Date, val attendance: MutableList<String>)
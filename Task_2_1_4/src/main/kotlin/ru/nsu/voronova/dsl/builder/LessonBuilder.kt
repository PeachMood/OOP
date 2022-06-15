package ru.nsu.voronova.dsl.builder

import ru.nsu.voronova.dsl.model.Lesson
import java.text.SimpleDateFormat

class LessonBuilder {
    private val attendance: MutableList<String> = ArrayList()
    var date: String = ""

    fun attendance(vararg surnames: String) {
        attendance.addAll(surnames.asList())
    }

    fun build(): Lesson {
        require(date != "") { "Date of the lesson must be specified." }
        return Lesson(SimpleDateFormat("dd/MM/yyyy").parse(date), attendance)
    }
}
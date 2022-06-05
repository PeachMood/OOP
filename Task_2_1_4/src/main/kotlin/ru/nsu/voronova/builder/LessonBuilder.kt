package ru.nsu.voronova.builder

import ru.nsu.voronova.model.Lesson
import ru.nsu.voronova.model.Student
import java.text.SimpleDateFormat

class LessonBuilder  {
    var date: String = ""
    private val attendance: MutableList<Student> = ArrayList()

    fun addStudents(students: Collection<Student>) {
        attendance.addAll(students)
    }

    fun build(): Lesson {
        if (date  == "") {
            throw IllegalArgumentException("Date of the lesson must be specified.")
        }
        return Lesson(SimpleDateFormat("dd/MM/yyyy").parse(date), attendance)
    }
}
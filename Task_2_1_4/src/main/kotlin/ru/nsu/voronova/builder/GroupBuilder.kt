package ru.nsu.voronova.builder

import ru.nsu.voronova.list.Lessons
import ru.nsu.voronova.list.Students
import ru.nsu.voronova.model.Group
import ru.nsu.voronova.model.Lesson
import ru.nsu.voronova.model.Student

class GroupBuilder {
    var name: String = ""
    private val students: MutableList<Student> = ArrayList()
    private val lessons: MutableList<Lesson> = ArrayList()

    fun LessonBuilder.attendance(vararg surnames: String) {
        this.addStudents(students.filter { student -> student.surname in surnames })
    }

    fun students(block: Students.() -> Unit) {
        students.addAll(Students().apply(block))
    }

    fun lessons(block: Lessons.() -> Unit) {
        lessons.addAll(Lessons().apply(block))
    }

    fun build(): Group {
        if (name == "") {
            throw IllegalArgumentException("Name of the group must be specified.")
        }
        if (students.size == 0) {
            throw IllegalArgumentException("Students of the group must be specified.")
        }
        if (lessons.size == 0) {
            throw IllegalArgumentException("The classes that were held for the group must be specified.")
        }
        return Group(name, students, lessons)
    }
}
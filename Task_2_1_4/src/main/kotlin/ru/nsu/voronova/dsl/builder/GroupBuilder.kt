package ru.nsu.voronova.dsl.builder

import ru.nsu.voronova.dsl.list.Lessons
import ru.nsu.voronova.dsl.list.Students
import ru.nsu.voronova.dsl.model.Group
import ru.nsu.voronova.dsl.model.Lesson
import ru.nsu.voronova.dsl.model.Student

class GroupBuilder {
    private val students: MutableList<Student> = ArrayList()
    private val lessons: MutableList<Lesson> = ArrayList()
    var name: String = ""

    fun students(block: Students.() -> Unit) {
        students.addAll(Students().apply(block))
    }

    fun lessons(block: Lessons.() -> Unit) {
        lessons.addAll(Lessons().apply(block))
    }

    fun build(): Group {
        require(name != "") { "Name of the group must be specified." }
        require(students.size != 0) { "Students of the group must be specified." }
        require(lessons.size != 0) { "The classes that were held for the group must be specified." }
        return Group(name, students, lessons)
    }
}
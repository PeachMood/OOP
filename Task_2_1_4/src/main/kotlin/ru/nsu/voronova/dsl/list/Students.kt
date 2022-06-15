package ru.nsu.voronova.dsl.list

import ru.nsu.voronova.dsl.builder.StudentBuilder
import ru.nsu.voronova.dsl.model.Student

class Students : ArrayList<Student>() {
    fun student(block: StudentBuilder.() -> Unit) {
        add(StudentBuilder().apply(block).build())
    }
}
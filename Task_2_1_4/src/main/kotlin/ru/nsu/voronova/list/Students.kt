package ru.nsu.voronova.list

import ru.nsu.voronova.builder.StudentBuilder
import ru.nsu.voronova.model.Student

class Students : ArrayList<Student>() {
    fun student(block: StudentBuilder.() -> Unit) {
        add(StudentBuilder().apply(block).build())
    }
}
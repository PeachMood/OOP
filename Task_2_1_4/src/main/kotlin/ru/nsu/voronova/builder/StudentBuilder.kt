package ru.nsu.voronova.builder

import ru.nsu.voronova.model.Student
import ru.nsu.voronova.model.Task
import java.net.URL

class StudentBuilder {
    var nickname: String = ""
    var name: String = ""
    var surname: String = ""
    var repository: String = ""
    private val givenTasks: MutableList<Task> = ArrayList()

    fun addTasks(tasks: Collection<Task>) {
        this.givenTasks.addAll(tasks)
    }

    fun build(): Student {
        if (nickname == "") {
            throw IllegalArgumentException("Nickname of the student must be specified.")
        }
        if (name == "") {
            throw IllegalArgumentException("Name of the student must be specified.")
        }
        if (surname == "") {
            throw IllegalArgumentException("Surname of the student must be specified.")
        }
        if (repository == "") {
            throw IllegalArgumentException("Repository of the student must be specified.")
        }
        return Student(nickname, name, surname, URL(repository), givenTasks)
    }
}

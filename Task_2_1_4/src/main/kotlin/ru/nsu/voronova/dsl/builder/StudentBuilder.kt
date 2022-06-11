package ru.nsu.voronova.dsl.builder

import ru.nsu.voronova.dsl.model.Student
import java.net.URL

class StudentBuilder {
    private val assignedTasks: MutableList<Int> = ArrayList()
    var nickname: String = ""
    var name: String = ""
    var surname: String = ""
    var repository: String = ""

    fun assignedTasks(vararg tasks: Int) {
        assignedTasks.addAll(tasks.asList())
    }

    fun build(): Student {
        require(nickname != "") { "Nickname of the student must be specified." }
        require(name != "") { "Name of the student must be specified." }
        require(surname != "") { "Surname of the student must be specified." }
        require(repository != "") { "Repository of the student must be specified." }
        return Student(nickname, name, surname, URL(repository), assignedTasks)
    }
}

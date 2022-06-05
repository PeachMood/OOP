package ru.nsu.voronova.builder


import ru.nsu.voronova.model.Task
import java.text.SimpleDateFormat

class TaskBuilder {
    var id: Int = -1
    var name: String = ""
    var points: Int = -1
    var deadline: String = ""

    fun build(): Task {
        if (id == -1) {
            throw IllegalArgumentException("Id of the task must be specified.")
        }
        if (name == "") {
            throw IllegalArgumentException("Name of the task must be specified.")
        }
        if (points == -1) {
            throw IllegalArgumentException("Points must be specified for the task.")
        }
        if (deadline == "")
            throw IllegalArgumentException("Deadline must be specified for the task.")
        return Task(id, name, points, SimpleDateFormat("dd/MM/yyyy").parse(deadline))
    }
}
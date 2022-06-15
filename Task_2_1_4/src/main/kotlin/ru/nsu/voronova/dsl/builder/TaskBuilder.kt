package ru.nsu.voronova.dsl.builder


import ru.nsu.voronova.dsl.model.Task
import java.text.SimpleDateFormat

class TaskBuilder {
    var id: Int = -1
    var name: String = ""
    var points: Int = -1
    var deadline: String = ""

    fun build(): Task {
        require(id != -1) { "Id of the task must be specified." }
        require(name != "") { "Name of the task must be specified." }
        require(points != -1) { "Points must be specified for the task." }
        require(deadline != "") { "Deadline must be specified for the task." }
        return Task(id, name, points, SimpleDateFormat("dd/MM/yyyy").parse(deadline))
    }
}
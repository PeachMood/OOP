package ru.nsu.voronova.dsl.builder

import ru.nsu.voronova.dsl.list.Checkpoints
import ru.nsu.voronova.dsl.list.Groups
import ru.nsu.voronova.dsl.list.Tasks
import ru.nsu.voronova.dsl.model.Checkpoint
import ru.nsu.voronova.dsl.model.Course
import ru.nsu.voronova.dsl.model.Group
import ru.nsu.voronova.dsl.model.Task

fun course(block: CourseBuilder.() -> Unit): Course = CourseBuilder().apply(block).build()

open class CourseBuilder {
    private val tasks: MutableList<Task> = ArrayList()
    private val groups: MutableList<Group> = ArrayList()
    private val checkpoints: MutableList<Checkpoint> = ArrayList()

    fun tasks(block: Tasks.() -> Unit) {
        tasks.addAll(Tasks().apply(block))
    }

    fun groups(block: Groups.() -> Unit) {
        groups.addAll(Groups().apply(block))
    }

    fun checkpoints(block: Checkpoints.() -> Unit) {
        checkpoints.addAll(Checkpoints().apply(block))
    }

    fun build(): Course {
        require(tasks.size != 0) { "Tasks must be specified fro the course." }
        require(groups.size != 0) { "Groups must be specified fro the course." }
        require(checkpoints.size != 0) { "Checkpoints must be specified fro the course." }
        return Course(tasks, groups, checkpoints)
    }
}

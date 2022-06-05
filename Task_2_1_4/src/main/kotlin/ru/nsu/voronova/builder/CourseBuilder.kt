package ru.nsu.voronova.builder

import ru.nsu.voronova.list.Checkpoints
import ru.nsu.voronova.list.Groups
import ru.nsu.voronova.list.Tasks
import ru.nsu.voronova.model.Checkpoint
import ru.nsu.voronova.model.Course
import ru.nsu.voronova.model.Group
import ru.nsu.voronova.model.Task

fun course(block: CourseBuilder.() -> Unit): Course = CourseBuilder().apply(block).build()

class CourseBuilder {
    private val tasks: MutableList<Task> = ArrayList()
    private val groups: MutableList<Group> = ArrayList()
    private val checkpoints: MutableList<Checkpoint> = ArrayList()

    fun StudentBuilder.givenTasks(vararg ids: Int) {
        this.addTasks(tasks.filter { task -> task.id in ids })
    }

    fun StudentBuilder.givenTasks(vararg names: String) {
        this.addTasks(tasks.filter { task -> task.name in names })
    }

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
        if (tasks.size == 0) {
            throw IllegalArgumentException("Tasks must be specified fro the course.")
        }
        if (groups.size == 0) {
            throw IllegalArgumentException("Groups must be specified fro the course.")
        }
        if (checkpoints.size == 0) {
            throw IllegalArgumentException("Checkpoints must be specified fro the course.")
        }
        return Course(tasks, groups, checkpoints)
    }
}

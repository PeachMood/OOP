package ru.nsu.voronova.dsl.list

import ru.nsu.voronova.dsl.builder.TaskBuilder
import ru.nsu.voronova.dsl.model.Task

class Tasks : ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit) {
        add(TaskBuilder().apply(block).build())
    }
}
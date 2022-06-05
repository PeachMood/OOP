package ru.nsu.voronova.list

import ru.nsu.voronova.builder.TaskBuilder
import ru.nsu.voronova.model.Task

class Tasks : ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit) {
        add(TaskBuilder().apply(block).build())
    }
}
package ru.nsu.voronova.model

data class Course(val tasks: List<Task>, val groups: List<Group>, val checkpoints: List<Checkpoint>)

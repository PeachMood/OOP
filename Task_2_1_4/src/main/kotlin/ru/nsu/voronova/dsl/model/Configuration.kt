package ru.nsu.voronova.dsl.model

data class Configuration(val tasks: List<Task>, val groups: List<Group>, val checkpoints: List<Checkpoint>)

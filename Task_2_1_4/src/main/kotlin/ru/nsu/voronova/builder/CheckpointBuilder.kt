package ru.nsu.voronova.builder

import ru.nsu.voronova.model.Checkpoint
import java.text.SimpleDateFormat
import kotlin.IllegalArgumentException

class CheckpointBuilder {
    private var name: String = ""
    private var date: String = ""

    fun checkpoint(name: String, date: String) {
        this.name = name
        this.date = date
    }

    fun build(): Checkpoint {
        if (name == "") {
            throw IllegalArgumentException("Name of the checkpoint must be specified.")
        }
        if (date == "") {
            throw IllegalArgumentException("Date of the checkpoint must be specified.")
        }
        return Checkpoint(name, SimpleDateFormat("dd/MM/yyyy").parse(date))
    }
}
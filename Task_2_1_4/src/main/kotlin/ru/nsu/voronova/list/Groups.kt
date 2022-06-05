package ru.nsu.voronova.list

import ru.nsu.voronova.builder.GroupBuilder
import ru.nsu.voronova.model.Group

class Groups : ArrayList<Group>() {
    fun group(block: GroupBuilder.() -> Unit) {
        add(GroupBuilder().apply(block).build())
    }
}
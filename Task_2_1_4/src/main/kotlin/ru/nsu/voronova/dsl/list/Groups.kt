package ru.nsu.voronova.dsl.list

import ru.nsu.voronova.dsl.builder.GroupBuilder
import ru.nsu.voronova.dsl.model.Group

class Groups : ArrayList<Group>() {
    fun group(block: GroupBuilder.() -> Unit) {
        add(GroupBuilder().apply(block).build())
    }
}
package ru.nsu.voronova.dsl.list

import ru.nsu.voronova.dsl.builder.LessonBuilder
import ru.nsu.voronova.dsl.model.Lesson

class Lessons : ArrayList<Lesson>() {
    fun lesson(block: LessonBuilder.() -> Unit) {
        add(LessonBuilder().apply(block).build())
    }

}
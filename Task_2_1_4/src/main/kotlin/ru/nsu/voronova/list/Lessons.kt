package ru.nsu.voronova.list

import ru.nsu.voronova.builder.LessonBuilder
import ru.nsu.voronova.model.Lesson

class Lessons : ArrayList<Lesson>() {
    fun lesson(block: LessonBuilder.() -> Unit) {
        add(LessonBuilder().apply(block).build())
    }

}
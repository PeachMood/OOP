class Lessons : ArrayList<Lesson>() {
    fun lesson(block: LessonBuilder.() -> Unit) = add(LessonBuilder().apply(block).build())

}
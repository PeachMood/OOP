class GroupBuilder {
    var name: String = ""
    private val students: MutableList<Student> = ArrayList()
    private val lessons: MutableList<Lesson> = ArrayList()

    fun LessonBuilder.attendance(vararg surnames: String) {
        this.addStudents(students.filter { student -> student.surname in surnames })
    }

    fun students(block: Students.() -> Unit) = students.addAll(Students().apply(block))
    fun lessons(block: Lessons.() -> Unit) = lessons.addAll(Lessons().apply(block))
    fun build(): Group = Group(name, students, lessons)
}
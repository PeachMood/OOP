import java.text.SimpleDateFormat

class LessonBuilder {
    var date: String = ""
    private val attendance: MutableList<Student> = ArrayList()
    fun addStudents(students: Collection<Student>) {
        attendance.addAll(students)
    }

    fun build(): Lesson = Lesson(SimpleDateFormat("dd/MM/yyyy").parse(date), attendance)
}
class Students : ArrayList<Student>() {
    fun student(block: StudentBuilder.() -> Unit) = add(StudentBuilder().apply(block).build())
}
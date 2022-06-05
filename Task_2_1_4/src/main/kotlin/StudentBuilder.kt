import java.net.URL

class StudentBuilder {
    var nickname: String = ""
    var name: String = ""
    var surname: String = ""
    var repository: String = ""
    private val givenTasks: MutableList<Task> = ArrayList()
    fun addTasks(givenTasks: Collection<Task>) {
        this.givenTasks.addAll(givenTasks)
    }

    fun build(): Student = Student(nickname, name, surname, URL(repository), givenTasks)
}

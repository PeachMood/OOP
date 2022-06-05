import java.net.URL

data class Student(
    var nickname: String,
    var name: String,
    var surname: String,
    var repository: URL,
    val givenTasks: List<Task>)


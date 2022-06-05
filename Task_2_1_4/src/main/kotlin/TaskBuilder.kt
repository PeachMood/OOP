import java.text.SimpleDateFormat

class TaskBuilder {
    var id: Int = 0
    var name: String = ""
    var points: Int = 0
    var deadline: String = ""

    fun build() = Task(id, name, points, SimpleDateFormat("dd/MM/yyyy").parse(deadline))
}
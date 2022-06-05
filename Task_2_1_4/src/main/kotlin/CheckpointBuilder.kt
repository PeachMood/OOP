import java.text.SimpleDateFormat

class CheckpointBuilder {
    private var name: String = ""
    private var date: String = ""

    fun checkpoint(name: String, date: String) {
        this.name = name
        this.date = date
    }
    fun build(): Checkpoint = Checkpoint(name, SimpleDateFormat("dd/MM/yyyy").parse(date))
}
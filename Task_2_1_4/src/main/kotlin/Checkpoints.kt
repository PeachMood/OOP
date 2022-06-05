import java.text.SimpleDateFormat

class Checkpoints : ArrayList<Checkpoint>() {
    fun checkpoint(name: String, date: String) = add(Checkpoint(name, SimpleDateFormat("dd/MM/yyyy").parse(date)))
}
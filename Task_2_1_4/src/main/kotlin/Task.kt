import java.util.*

data class Task(val id: Int, var name: String, var points: Int, var deadline: Date) {
    @Override
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Task)
            return false
        else {
            if (other.id == this.id)
                return true
        }
        return false
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }
}

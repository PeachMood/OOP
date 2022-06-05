fun course(block: CourseBuilder.() -> Unit): Course = CourseBuilder().apply(block).build()

class CourseBuilder {
    private val tasks: MutableList<Task> = ArrayList()
    private val groups: MutableList<Group> = ArrayList()
    private val checkpoints: MutableList<Checkpoint> = ArrayList()

    fun StudentBuilder.givenTasks(vararg ids: Int) {
        this.addTasks(tasks.filter { task -> task.id in ids })
    }

    fun StudentBuilder.givenTasks(vararg names: String) {
        this.addTasks(tasks.filter { task -> task.name in names })
    }

    fun tasks(block: Tasks.() -> Unit) {
        tasks.addAll(Tasks().apply(block))
    }

    fun groups(block: Groups.() -> Unit) {
        groups.addAll(Groups().apply(block))
    }

    fun checkpoints(block: Checkpoints.() -> Unit) {
        checkpoints.addAll(Checkpoints().apply(block))
    }

    fun build(): Course = Course(tasks, groups, checkpoints)
}

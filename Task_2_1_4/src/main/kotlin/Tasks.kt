class Tasks : ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit) =  add(TaskBuilder().apply(block).build())
}
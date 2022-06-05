class Groups : ArrayList<Group>() {
    fun group(block: GroupBuilder.() -> Unit) = add(GroupBuilder().apply(block).build())
}
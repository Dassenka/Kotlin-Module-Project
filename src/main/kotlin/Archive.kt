data class Archive(val name: String, val archivesList: MutableList<Note>){
    override fun toString(): String  = "$name"
}
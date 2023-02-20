import kotlin.system.exitProcess

class ArchiveMenu (private val dataList: MutableList<Archive>): AbstractMenu<Archive>(dataList){
    override val menuType: String = "Список архивов"
    override val menuType1: String = "архив"
    override val exit: String = "Для выхода из приложения"
    override val menuCreateNew: String = "Введите название нового архива:"

    override fun addNewElement(newName: String){
            dataList.add(Archive(newName, mutableListOf()))
            println("Архив $newName создан")
            startMenu()
    }

    override fun openElement(element: Archive){
        NoteMenu(this,element).startMenu()
    }

    override fun exitMenu() {
        println("Выходим из приложния. Возвращайтесь снова.")
        exitProcess(1)
    }
}
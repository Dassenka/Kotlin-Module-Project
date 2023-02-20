import java.util.Scanner
import kotlin.text.toIntOrNull as toIntOrNull1

class NoteMenu (private val parentMenu: ArchiveMenu, private val element: Archive): AbstractMenu<Note>(element.archivesList){
    override val menuType: String = "Список заметок в архиве $element:"
    override val menuType1: String = "заметку"
    override val menuCreateNew: String = "Введите название новой заметки:"
    override val exit: String = "Для возврата в меню со списком архивов"
    private val scanner = Scanner(System.`in`)

    override fun addNewElement(newName: String) {
            println("Введите содержание заметки")
            val text: String = scanner.nextLine()
            element.archivesList.add(Note(newName, text))
            println("Заметка $newName создана")
            startMenu()
    }

    override fun openElement(element: Note) {
        while (true) {
            noteMenuView(element)
            val userInput: String = scanner.nextLine()
            if (checkInput(userInput)) {
                when (userInput.toInt()) {
                    0 -> println("Содержание заметки $element:\n${element.text}")
                    1 -> addText(element)
                    2 -> changeText(element)
                    3 -> startMenu()
                }
            }
        }
    }

    override fun exitMenu() {
        println("Выходим из списка заметок архива $element:.")
        parentMenu.startMenu()
    }

    private fun noteMenuView(element: Note){
        println("Для работы с заметкой $element введите цифру:")
        println("0. Чтобы просмотреть текст заметки")
        println("1. Чтобы добавить текст в заметку")
        println("2. Чтобы целиком заменить текст заметки")
        println("3. Вернуться к списку заметок")
    }

    private fun checkInput (userInput: String): Boolean{
        userInput.toIntOrNull1()?.let {
            if (userInput.toInt() in 0..3 ){
                return true
            } else {
                println("Ошибка ввода. Такого числа нет в меню выбора")
                return false
            }
        }
        println("Ошибка ввода. Введена буква или ничего")
        return false
    }

    private fun addText(element: Note) {
        println("Дополните заметку")
        val newText: String = scanner.nextLine()
        element.text = element.text+ " " + newText
        println("Заметка $element отредактирована")
        openElement(element)
    }

    private fun changeText(element: Note) {
        println("Введите новый текст заметки")
        val newText: String = scanner.nextLine()
        element.text = newText
        println("Заметка $element отредактирована")
        openElement(element)
    }
}
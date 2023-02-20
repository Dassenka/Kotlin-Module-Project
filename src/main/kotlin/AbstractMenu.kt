import java.util.Scanner
import kotlin.text.toIntOrNull as toIntOrNull1

abstract class AbstractMenu<T> (private val dataList: MutableList<T>) {
    private val scanner = Scanner(System.`in`)
    abstract val menuType: String
    abstract val menuType1: String
    abstract val menuCreateNew: String
    abstract val exit: String

    fun startMenu() {
        while (true) {
            mainMenu()
            val userInput: String = scanner.nextLine()
            if (checkInput(userInput)) {
                selectedAction(userInput.toInt())
            }
        }
    }

    private fun mainMenu() {
        println("Для продолжения и выбора введите цифру:")
        println(menuType)
        println("0. Чтобы создать $menuType1")
        dataList.forEachIndexed { index, value -> println("${index+1}. $value") }
        println("${dataList.size+1}. $exit")
    }

    private fun checkInput(value: String): Boolean {
        value.toIntOrNull1()?.let {
            if (dataList.indices.contains(value.toInt()) || value.toInt() == 0 || value.toInt() == dataList.size || value.toInt() == dataList.size+1){
                return true
            } else {
                println("Ошибка ввода. Такого числа нет в меню выбора")
                return false
            }
        }
        println("Ошибка ввода. Введена буква или ничего")
        return false
    }

    private fun selectedAction(userInput: Int){
        when (userInput){
            0 -> addNew()
            in 1..dataList.size -> elementList(userInput)
            dataList.size+1 -> exitMenu()
        }
    }

    private fun elementList(index: Int) {
        openElement(dataList[index-1])
    }

    private fun addNew(){
        println(menuCreateNew)
        var newName: String = scanner.nextLine()
        while(true) {
            if (newName.isEmpty()) {
                println("Имя не может быть пустым. $menuCreateNew")
                newName = scanner.nextLine()
            }
            addNewElement(newName)
            startMenu()
        }
    }

    abstract fun addNewElement(newName: String)
    abstract fun openElement(element: T)
    abstract fun exitMenu()
}
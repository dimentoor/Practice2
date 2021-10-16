import java.util.*

open class Person(var name : String,
                  var birthYear : Int)
{
    var age : Int = Calendar.getInstance().get(Calendar.YEAR) - birthYear
}

class Student(name : String, 
              birthYear : Int, 
              var averageGrade : Double, 
              var extramural : Boolean = false ):Person(name, birthYear)
{
 override fun toString(): String = "Имя: $name, Год рождения: $birthYear, Средняя оценка: $averageGrade, Заочный студент: $extramural "
  
}

class Lecturer(name : String, 
               birthYear : Int, 
               var degree : String, 
               var experienceFrom : Int):Person(name, birthYear){
    
   override fun toString(): String = "Имя: $name, Год рождения: $birthYear, Ученая стпень: $degree, Год начала учебного стажа: $experienceFrom "  
   
}

fun sortByAge(persons:List<Person>):List<Person> {  
       
     val sortedList1 = persons.sortedWith(compareBy(Person::age)).reversed()
     println(sortedList1.joinToString( separator = "\n")) 
     return sortedList1
}

fun MutableList<Person>.sortByNameStudents():List<Student>
{
    return this.filter{ it is Student }.sortedByDescending{ it.name } as List<Student>
}

fun MutableList<Person>.sortByAverageGrade(exceptExtramural : Boolean):List<Student>
{
    // если false - выводить всех студентов
    if (exceptExtramural == false) return (this.filter{ it is Student } as List<Student>).sortedByDescending{ it.averageGrade } 
    //  иначе не выводить заочников
    else return (this.filter{ it is Student } as List<Student>).filter{ !it.extramural }.sortedByDescending{ it.averageGrade }
}

fun main() {
       
    val persons = mutableListOf<Person>(Student("Тремаскин Кирилл", 2000,5.0,false), 
                         Student("Баляйкин Илья", 2000,4.6,false), 
                         Student("Барабошкин Дмитрий", 1998,4.2,false),
                         Student("Коржов Александр", 1999,3.5,true),
                         Student("Карпов Михаил", 1997,4.0,false),
                         Lecturer("Гущина Оксана Александровна",1972,"Кандидат технических наук",2000),
                         Lecturer("Иванов Иван Иванович",1955,"Доктор технических наук",1980),
                         Lecturer("Комлева Наталья Станиславовна",1972,"Кандидат экономических наук",1998),
                         Lecturer("Петров Петр Александрович",1967,"Кандидат технических наук",1990),
                         Lecturer("Федоров Александр Павлович",1978,"Доктор физико-математических наук",2005))
     println("Список всех:")
     println(persons.joinToString( separator = "\n"))
     println()
     println("Cписок, отсортированный по возрасту")
     sortByAge(persons)
     println()
     println("Cписок студентов, отсортированный по имени")
     println(persons.sortByNameStudents().joinToString( separator = "\n"))
     println()
     println("Cписок студентов, отсортированный по средней оценке")
     println(persons.sortByAverageGrade(false).joinToString( separator = "\n"))
  
}

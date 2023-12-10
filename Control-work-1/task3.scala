class Human(var name:String, var age:Int)


class ListHuman {
	private var humans:List[Human] = List()

	def AddNewHuman(name:String, age:Int): Unit = {
		var newHuman = new Human(name, age) 
		humans = newHuman :: humans
	}

	def DeleteHuman(name:String): Unit = {
		humans = humans.filterNot(_.name == name)
	}

	def FindByName(name:String): Option[Human] = {
		humans.find(_.name == name)
	}

	def FilterByAge(age1:Int, age2:Int): List[Human] = {
		humans.filter(human => human.age >= age1 && human.age <= age2)
	}

	def PrintHumans(): Unit = {
		humans.foreach(human => println(s"Name: ${human.name}, Age: ${human.age}"))
	}
}

object Main {
	def main(args: Array[String]) = {

		var listHuman = new ListHuman()

		listHuman.AddNewHuman("Pavel", 16)
		listHuman.AddNewHuman("Egor", 35)
		listHuman.AddNewHuman("Vladimir", 28)
		listHuman.PrintHumans()
		println("")

		var searchHuman = listHuman.FindByName("Vladimir")

		searchHuman match {
		case Some(human) => println(s"Successfully. Human found: ${human.name}, ${human.age}")
		case None => println("Failure. Human not found")
		}
		println("")

		var filteredHumans = listHuman.FilterByAge(0, 30)
		filteredHumans.foreach(human => println(s"Name: ${human.name}, Age: ${human.age}"))
		println("")

		listHuman.DeleteHuman("Pavel")
		listHuman.PrintHumans()
	}
}
object Main {
  def main(args: Array[String]): Unit = {

//  ----------------------------------------------------|Offices|----------------------------------------------------
    var office1 = new Office

//  ----------------------------------------------------|Simulation|----------------------------------------------------
    var years = 3

    println(Console.GREEN + "Simulation started..." + Console.RESET + "\n")

    for (year <- 0 to years - 1) {      // цикл по кількості повторень симуляції (по рокам)
      Simulation.createStudents(office1)

      if (year == 0) {
        Simulation.createCoaches(office1)     // створення постійних викладачів
      }

      Simulation.formGroups(office1)
      Simulation.startSimulation(office1)

      println("\n" + Console.YELLOW_B + Console.BLACK + s"${"*" * 18}|Year ${year + 1} has ended|${"*" * 18}" + Console.RESET + "\n")
    }
    println(Console.GREEN + "Simulation finished." + Console.RESET)
  }

  // функція для округлення чисел
  def roundNumber(number: Double, AfterComma: Int): Double = {
    var formattedString: String = ""

    AfterComma match {
      case 1 => formattedString = String.format("%.1f", number)
      case 2 => formattedString = String.format("%.2f", number)
      case 3 => formattedString = String.format("%.3f", number)
      case 4 => formattedString = String.format("%.4f", number)
      case 5 => formattedString = String.format("%.5f", number)
      case _ => println("The limit of allowed numbers has been exceeded")
    }
    var newFormattedString: String = formattedString.replace(",", ".")
    var formattedNumber: Double = newFormattedString.toDouble

    return formattedNumber
  }

  // функція для генерації шансу
  def setChance(chance: Int): Boolean = {
    chance match {
      case 90 => if (Simulation.RandomNumber.between(1, 10+1) == 1) false else true
      case 75 => if (Simulation.RandomNumber.between(1, 4+1) == 1) false else true
      case 66 => if (Simulation.RandomNumber.between(1, 3+1) == 1) false else true
      case 50 => if (Simulation.RandomNumber.between(1, 2+1) == 1) true else false
      case 33 => if (Simulation.RandomNumber.between(1, 3+1) == 1) true else false
      case 25 => if (Simulation.RandomNumber.between(1, 4+1) == 1) true else false
      case 20 => if (Simulation.RandomNumber.between(1, 5+1) == 1) true else false
      case 10 => if (Simulation.RandomNumber.between(1, 10+1) == 1) true else false
      case 5 => if (Simulation.RandomNumber.between(1, 20+1) == 1) true else false
      case 1 => if (Simulation.RandomNumber.between(1, 100+1) == 1) true else false
      case _ => println("This chance isn't in the list of possible chances"); false
    }
  }
}
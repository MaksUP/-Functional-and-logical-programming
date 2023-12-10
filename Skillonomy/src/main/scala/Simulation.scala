import scala.util.Random

object Simulation {
  private var randomNumber = new Random()
  private var listCoursePrices: List[Int] = List.range(100, 1000+10, step = 10)

  def RandomNumber: Random = randomNumber

  def ListCoursePrices(): List[Int] = listCoursePrices
  def ListCoursePrices(newValue: List[Int]) = {listCoursePrices = newValue}

  // створення студентів
  def createStudents(office: Office): Unit = {
    for (i <- 0 to randomNumber.between(10-1, 30)) {
      var defaultStudent = new Student(name = "Name" + (i + 1), surname = "Surname" + (i + 1), age = randomNumber.between(16, 21 + 1),
                                       addr = new Address(country = "Country" + (i + 1), city = "City" + (i + 1)),
                                       balance = new Balance(myTokens = 0.0, myMoney = 4000.0),
                                       group = randomNumber.between(100, 400 + 1).toString, ID = i + 1, currentCourse = false, grades = List())
      office.addStudent(defaultStudent)
    }
  }

  // створення викладачів
  def createCoaches(office: Office): Unit = {
    for (i <- 0 to randomNumber.between(3-1, 5)) {
      var defaultCoach = new Coach(name = "Name" + (i + 1), surname = "Surname" + (i + 1), age = randomNumber.between(25, 40 + 1),
                                   addr = new Address(country = "Country" + (i + 1), city = "City" + (i + 1)),
                                   balance = new Balance(myTokens = 0.0, myMoney = 8000.0),
                                   сoursePrice = listCoursePrices.apply(randomNumber.nextInt(listCoursePrices.length)),
                                   trainingPeriod = randomNumber.between(3, 12 + 1), ID = i + 1, listStudents = List())
      office.addCoach(defaultCoach)
    }
  }

  // додавання студента до рандомного викладача та запис на його курс
  def formGroups(office: Office): Unit = {
    for (st <- 0 to office.OfficeListStudents.length - 1) {
      var currentStudent = office.OfficeListStudents.apply(st)
      var randomCoach = office.OfficeListCoaches.apply(randomNumber.nextInt(office.OfficeListCoaches.length))

      currentStudent.CurrentCourse(true)    // запис на курс
      randomCoach.addStudent(currentStudent)
    }
  }

  // симуляція навчального процесу, фінансових операцій та змін на ринку
  def startSimulation(office: Office): Unit = {

    println(s"Students in the office: ${office.OfficeListStudents.length}")
    println(s"Coaches in the office: ${office.OfficeListCoaches.length}\n")

    for (month <- 1 to 12) {    // цикл по місяцям

      for (co <- 0 to office.OfficeListCoaches.length - 1) {     // цикл по викладачам (курси починаються з 1-го місяця у всіх)
        var currentCoach = office.OfficeListCoaches.apply(co)

        if (!currentCoach.ListStudents.isEmpty) {
          var salary = currentCoach.payroll(currentCoach) // видача зарплати

          for (st <- 0 to currentCoach.ListStudents.length - 1) { // цикл по студентам
            var currentStudent = currentCoach.ListStudents.apply(st)

            def payForCourse(currentStudent: Student): Unit = {
              if (currentStudent.CurrentCourse) {

                if (currentStudent.Balance.MyTokens >= currentCoach.CoursePrice) {

                  currentStudent.Balance.MyTokens(currentStudent.Balance.MyTokens - currentCoach.CoursePrice) // плата за курс
                  currentStudent.addGrade(randomNumber.nextInt(5) + 1) // додавання рандомної оцінки
                  currentStudent.accrueScholarship(currentStudent, currentCoach.CoursePrice) // видача стипендії
                }
                else {
//                  println("Not enough tokens to continue learning.")
                  var missingTokens = currentCoach.CoursePrice - currentStudent.Balance.MyTokens

                  if (currentStudent.buyTokens(currentStudent, missingTokens)) {
                    payForCourse(currentStudent: Student)
                  }
                  else {
                    currentStudent.CurrentCourse(false) // Відрахування з курсу
                    office.addExpelledStudent(currentStudent)
                  }
                }
              }
            }
            payForCourse(currentStudent: Student)

            var excessTokens = currentStudent.Balance.MyTokens - currentCoach.CoursePrice
            if (excessTokens > 0) {
              if (Main.setChance(50)) {
                currentStudent.sellTokens(currentStudent, excessTokens)
              }
            }
          }
          currentCoach.sellTokens(currentCoach, salary * 0.01 * 95) // вчителі продають 95% своєї зарплати

          if (currentCoach.TrainingPeriod == month) {
            currentCoach.ListStudents.foreach(st => if (Main.setChance(50)) {
                st.sellTokens(st, st.Balance.MyTokens)
            })
            println(Console.BLUE + s"Coach ${currentCoach.Name} ${currentCoach.Surname}'s course is over" + Console.RESET)
            println("-----[ Coach ]-----")
            currentCoach.printCoach()
            println("-----[ Students at the coach ]-----")
            currentCoach.printAllStudents()

            currentCoach.ListStudents(List())
          }
        }
      }

//      def expelStudentAtWill(): Unit = { // Відрахування студента за власним бажанням
//        if (Main.setChance(5)) {
//          var expelledStudent = office.OfficeListStudents.apply(randomNumber.nextInt(office.OfficeListStudents.length))
//          expelledStudent.CurrentCourse(false)
//          office.addExpelledStudent(expelledStudent)
//        }
//      }
    }
//    println(Console.RED + "-----[ Expelled students ]-----" + Console.RESET)
//    office.printAllExpelledStudents()
    println(Console.GREEN + s"Number of students who have completed the course: " +
                            s"${office.OfficeListStudents.length - office.OfficeExpelledStudents.length}" + Console.RESET)

    println(Console.RED + s"Number of expelled students: " +
                          s"${office.OfficeExpelledStudents.length}" + Console.RESET)

    office.OfficeListStudents(List())
    office.OfficeExpelledStudents(List())
  }
}
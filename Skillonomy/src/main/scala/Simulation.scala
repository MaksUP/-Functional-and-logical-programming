import scala.util.Random

object Simulation {
  private var randomNumber = new Random()
  private var listCoursePrices: List[Int] = List.range(100, 1000+10, step = 10)     // рандомні ціни курсу

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
                                   trainingPeriod = randomNumber.between(3, 12 + 1), ID = i + 1, currentCourse = false, listStudents = List())
      office.addCoach(defaultCoach)
    }
  }

  // створення курсу, додавання студента до рандомного викладача та запис на його курс
  def formGroups(office: Office): Unit = {
    for (co <- 0 to office.OfficeListCoaches.length - 1) {
      var currentCoach = office.OfficeListCoaches.apply(co)

      currentCoach.CurrentCourse(true)    // створення курсу
    }

    for (st <- 0 to office.OfficeListStudents.length - 1) {
      var currentStudent = office.OfficeListStudents.apply(st)
      var randomCoach = office.OfficeListCoaches.apply(randomNumber.nextInt(office.OfficeListCoaches.length))

      currentStudent.CurrentCourse(true)    // запис на курс
      randomCoach.addStudent(currentStudent)
    }
  }

  // симуляція навчального процесу, фінансових операцій та змін на ринку
  def startSimulation(office: Office): Unit = {

    // плата за курс, навчання та видача стипендії
    def payForCourse(currentStudent: Student, сoursePrice: Int): Boolean = {
      if (currentStudent.Balance.MyTokens >= сoursePrice) {

        currentStudent.Balance.MyTokens(currentStudent.Balance.MyTokens - сoursePrice)    // плата за курс
        currentStudent.addGrade(randomNumber.nextInt(5) + 1)      // додавання рандомної оцінки
        currentStudent.accrueScholarship(currentStudent, сoursePrice)     // видача стипендії
        return true
      }
      else return false
    }

    // купівля бракуючих токенів
    def buyMissingTokens(currentStudent: Student, сoursePrice: Int): Boolean = {
//      println("Not enough tokens to continue learning.")
      var missingTokens = сoursePrice - currentStudent.Balance.MyTokens     // визначення кількості бракуючих токенів

      if (currentStudent.buyTokens(currentStudent, missingTokens)) {      // купівля токенів
        return true
      }
      else return false
    }

    // відрахування студента
    def expelStudent(currentStudent: Student): Unit = {
      currentStudent.CurrentCourse(false)     // відрахування з курсу
      office.addExpelledStudent(currentStudent)     // додавання студента до списку відрахованих
    }

    // продаж надлишку токенів
    def sellExcessTokens(currentStudent: Student, сoursePrice: Int): Unit = {
      var excessTokens = currentStudent.Balance.MyTokens - сoursePrice      // визначення кількості надлишкових токенів

      if (excessTokens > 0) {
        if (Main.setChance(50)) {
          currentStudent.sellTokens(currentStudent, excessTokens)     // продаж токенів з шансом 50%
        }
      }
    }

    // продаж зарплати
    def sellSalary(currentCoach: Coach, salary: Double): Unit = {
      currentCoach.sellTokens(currentCoach, salary * 0.01 * 95)     // викладачі продають 95% своєї зарплати
    }

    // відрахування студента за власним бажанням
    def expelStudentAtWill(): Unit = {
      if (Main.setChance(5)) {
        var run = true      // змінна для зупинки нескінченного циклу

        while (run) {
          var expelledStudent = office.OfficeListStudents.apply(randomNumber.nextInt(office.OfficeListStudents.length))  // визначення рандомного
                                                                                                                         // студента
          if (expelledStudent.CurrentCourse) {      // перевірка чи не відраховано вже студента
            expelledStudent.CurrentCourse(false)      // відрахування студента з шансом 5%
            office.addExpelledStudent(expelledStudent)
            run = false     // зупинка циклу
          }
        }
      }
    }

    // перевірка на закінчення курсу
    def isCourseOver(currentCoach: Coach, month: Int): Unit = {
      if (currentCoach.TrainingPeriod == month) {     // перевірка чи дорівнює тривалість курсу викладача поточному місяцю
        currentCoach.CurrentCourse(false)     // завершення курсу

        currentCoach.ListStudents.foreach(st => if (Main.setChance(50)) {
          st.sellTokens(st, st.Balance.MyTokens)      // продаж всіх токенів студентів в кінці курсу з шансом 50%
        })
      }
    }

    // вивід початкової інформації
    println(Console.CYAN + s"Students in the office: ${office.OfficeListStudents.length}" + Console.RESET)
    println(Console.CYAN + s"Coaches in the office: ${office.OfficeListCoaches.length}" + Console.RESET + "\n")

    for (month <- 1 to 12) {      // цикл по місяцям
      expelStudentAtWill()      // відрахування студента за власним бажанням

      for (co <- 0 to office.OfficeListCoaches.length - 1) {      // цикл по викладачам (курси починаються з 1-го місяця у всіх)
        var currentCoach = office.OfficeListCoaches.apply(co)

        if (currentCoach.CurrentCourse) {     // перевірка чи закінчився курс у викладача
          var salary = currentCoach.payroll(currentCoach)     // видача зарплати
          sellSalary(currentCoach, salary)

          for (st <- 0 to currentCoach.ListStudents.length - 1) {     // цикл по студентам
            var currentStudent = currentCoach.ListStudents.apply(st)

            if (currentStudent.CurrentCourse) {     // перевірка чи навчається студент на курсі
              if (payForCourse(currentStudent, currentCoach.CoursePrice)) {     // спроба заплатити за курс
                sellExcessTokens(currentStudent, currentCoach.CoursePrice)     // в разі успіху - продаж надлишку токенів
              }
              else if (buyMissingTokens(currentStudent, currentCoach.CoursePrice)) {    // в разі невдачі - спроба купити бракуючі токени
                payForCourse(currentStudent, currentCoach.CoursePrice)      // якщо вдалось - заплатити за курс
              }
              else {
                expelStudent(currentStudent)      // якщо ні - відрахування студента
              }
            }
          }
        }
        isCourseOver(currentCoach, month)     // перевірка чи закінчився курс
      }
    }

    // вивід результатів за рік
    office.OfficeListCoaches.foreach(co => {
      if (co.ListStudents.length > 0) {
        println(Console.BLUE + s"Coach ${co.Name} ${co.Surname}'s course is over" + Console.RESET)
        println("-----[ Coach ]-----")
        co.printCoach()
        println("-----[ Students at the coach ]-----")
        co.printAllStudents()
        co.ListStudents(List())     // очищення списку студентів у викладача
      }
    })

    println(Console.RED + "-----[ Expelled students ]-----" + Console.RESET)
    office.printAllExpelledStudents()

    println(Console.MAGENTA + s"Number of tokens purchased: ${Exchange.PurchasedTokens}" + Console.RESET)
    println(Console.MAGENTA + s"Number of tokens sold: ${Exchange.SoldTokens}" + Console.RESET)
    println(Console.MAGENTA + s"Token price: ${Exchange.TokenPrice}" + Console.RESET)

    println(Console.GREEN + s"Number of students who have completed the course: " +
                            s"${office.OfficeListStudents.length - office.OfficeExpelledStudents.length}" + Console.RESET)

    println(Console.RED + s"Number of expelled students: ${office.OfficeExpelledStudents.length}" + Console.RESET)

    office.OfficeListStudents(List())     // очищення списку всіх студентів в офісі
    office.OfficeExpelledStudents(List())     // очищення списку відрахованих студентів
  }
}
class Office {
  private var officeListStudents: List[Student] = List()      // загальний список студентів
  private var officeListCoaches: List[Coach] = List()       // загальний список викладачів
  private var officeExpelledStudents: List[Student] = List()      // загальний список відрахованих студентів

  // методи списку студентів
  def OfficeListStudents: List[Student] = officeListStudents
  def OfficeListStudents(newValue: List[Student]) = {officeListStudents = newValue}

  def addStudent(student: Student): Unit = {
    officeListStudents = officeListStudents :+ student
  }

  def deleteStudent(ID: Int): Unit = {
    if (ID <= 0) {
      println("ID cannot be negative or zero \n")
    } else if (ID > officeListStudents.length) {
      println("This ID does not exist \n")
    } else {
      officeListStudents = officeListStudents.filter(_.getID != ID)
    }
  }

  def searchStudent(ID: Int): Unit = {
    if (ID <= 0) {
      println("ID cannot be negative or zero \n")
    } else if (ID > officeListStudents.length) {
      println("This ID does not exist \n")
    } else {
      (officeListStudents.find(_.getID == ID)) match {
        case Some(value) => println("Student found successfully \n")
                            officeListStudents.apply(ID - 1).printStudent()
        case None => println("Student not found \n")
      }
    }
  }

  def printAllStudents(): Unit = {
    if (officeListStudents.length != 0) {
      officeListStudents.foreach(stud => stud.printStudent())
    }
    else {
      println("None \n")
    }
  }


  // методи списку викладачів
  def OfficeListCoaches: List[Coach] = officeListCoaches
  def OfficeListCoaches(newValue: List[Coach]) = {officeListCoaches = newValue}

  def addCoach(coach: Coach): Unit = {
    officeListCoaches = officeListCoaches :+ coach
  }

  def deleteCoach(ID: Int): Unit = {
    if (ID <= 0) {
      println("ID cannot be negative or zero \n")
    } else if (ID > officeListCoaches.length) {
      println("This ID does not exist \n")
    } else {
      officeListCoaches = officeListCoaches.filter(_.getID != ID)
    }
  }

  def searchCoach(ID: Int): Unit = {
    if (ID <= 0) {
      println("ID cannot be negative or zero \n")
    } else if (ID > officeListCoaches.length) {
      println("This ID does not exist \n")
    } else {
      (officeListCoaches.find(_.getID == ID)) match {
        case Some(value) => println("Coach found successfully \n")
                            officeListCoaches.apply(ID - 1).printCoach()
        case None => println("Coach not found \n")
      }
    }
  }

  def printAllCoaches(): Unit = {
    if (officeListCoaches.length != 0) {
      officeListCoaches.foreach(coach => coach.printCoach())
    }
    else {
      println("None \n")
    }
  }


  // методи списку відрахованих студентів
  def OfficeExpelledStudents: List[Student] = officeExpelledStudents
  def OfficeExpelledStudents(newValue: List[Student]) = {officeExpelledStudents = newValue}

  def addExpelledStudent(expelledStudent: Student): Unit = {
    officeExpelledStudents = officeExpelledStudents :+ expelledStudent
  }

  def printAllExpelledStudents(): Unit = {
    if (officeExpelledStudents.length != 0) {
      officeExpelledStudents.foreach(exStud => exStud.printStudent())
    }
    else {
      println("None \n")
    }
  }
}
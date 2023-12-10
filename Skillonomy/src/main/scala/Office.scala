class Office {
  private var officeListStudents: List[Student] = List()
  private var officeListCoaches: List[Coach] = List()
  private var officeExpelledStudents: List[Student] = List()

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
    officeListStudents.foreach(stud => stud.printStudent())
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
    officeListCoaches.foreach(coach => coach.printCoach())
  }


  // методи списку відрахованих студентів
  def OfficeExpelledStudents: List[Student] = officeExpelledStudents
  def OfficeExpelledStudents(newValue: List[Student]) = {officeExpelledStudents = newValue}

  def addExpelledStudent(expelledStudent: Student): Unit = {
    officeExpelledStudents = officeExpelledStudents :+ expelledStudent
  }

  def printAllExpelledStudents(): Unit = {
    officeExpelledStudents.foreach(exStud => exStud.printStudent())
  }
}
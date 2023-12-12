class Coach(name: String, surname: String, age: Int, addr: Address, balance:Balance, var сoursePrice: Int, var trainingPeriod: Int, var ID: Int, var currentCourse: Boolean, var listStudents: List[Student])
  extends Human(name, surname, age, addr, balance) with Platform_API with Exchange_API {

  private var _сoursePrice: Int = сoursePrice
  private var _trainingPeriod: Int = trainingPeriod
  private var _ID: Int = ID
  private var _currentCourse: Boolean = currentCourse
  private var _listStudents: List[Student] = listStudents

  override def toString(): String = super.toString() + s", ${_сoursePrice}, ${_trainingPeriod}, ${_ID}, ${_listStudents}, ${_listStudents.length}"
  def printCoach(): Unit = {
    println(super.printHuman() + s"Course price: ${_сoursePrice} \nTraining period: ${_trainingPeriod} " +
                                 s"\nPersonal ID: ${_ID} \nNumber of students: ${_listStudents.length} \n")
  }

  def CoursePrice: Int = _сoursePrice
  def CoursePrice(newValue: Int) = {_сoursePrice = newValue}

  def TrainingPeriod: Int = _trainingPeriod
  def TrainingPeriod(newValue: Int) = {_trainingPeriod = newValue}

  def getID: Int = _ID
  def setID(newValue: Int) = {_ID = newValue}

  def CurrentCourse: Boolean = _currentCourse
  def CurrentCourse(newValue: Boolean) = {_currentCourse = newValue}

  def ListStudents: List[Student] = _listStudents
  def ListStudents(newValue: List[Student]) = {_listStudents = newValue}

  def addStudent(student: Student): Unit = {
    _listStudents = _listStudents :+ student
  }

  def deleteStudent(ID: Int): Unit = {
    if (ID <= 0) {
      println("ID cannot be negative or zero \n")
    } else if (ID > _listStudents.length) {
      println("This ID does not exist \n")
    } else {
      _listStudents = _listStudents.filter(_.getID != ID)
    }
  }

  def searchStudent(ID: Int): Unit = {
    if (ID <= 0) {
      println("ID cannot be negative or zero \n")
    } else if (ID > _listStudents.length) {
      println("This ID does not exist \n")
    } else {
      (_listStudents.find(_.getID == ID)) match {
        case Some(value) => println("Student found successfully \n")
                            _listStudents.apply(ID-1).printStudent()
        case None => println("Student not found \n")
      }
    }
  }

  def printAllStudents(): Unit = {
    if (_listStudents.length != 0) {
      _listStudents.foreach(st => st.printStudent())
    }
    else {
      println("None \n")
    }
  }
}
class Student(name: String, surname: String, age: Int, addr: Address, balance:Balance, var group: String, var ID: Int, var currentCourse: Boolean, var grades: List[Int])
  extends Human(name, surname, age, addr, balance) with Platform_API with Exchange_API {

  private var _group: String = group
  private var _ID: Int = ID
  private var _currentCourse: Boolean = currentCourse
  private var _grades: List[Int] = grades

  override def toString(): String = super.toString() + s", ${_group}, ${_ID}, ${_currentCourse}, ${_grades}"
  def printStudent(): Unit = {
    println(super.printHuman() + s"Group: ${_group} \nPersonal ID: ${_ID} \nCourse: ${if (_currentCourse) Console.GREEN + "Finished" + Console.RESET
            else Console.RED + "Not finished" + Console.RESET} \nGrades: ${if (_grades.nonEmpty) {_grades.mkString(", ")} else "none"} \n")
  }

  def Group: String = _group
  def Group(newValue: String) = {_group = newValue}

  def getID: Int = _ID
  def setID(newValue: Int) = {_ID = newValue}

  def CurrentCourse: Boolean = _currentCourse
  def CurrentCourse(newValue: Boolean) = {_currentCourse = newValue}

  def Grades: List[Int] = _grades
  def Grades(newValue: List[Int]) = {_grades = newValue}

  def addGrade(grade: Int): Unit = {
    _grades = _grades :+ grade
  }

  def printGrades(): Unit = {
    println(s"Grades: ${_grades.mkString(", ")} \n")
  }
}
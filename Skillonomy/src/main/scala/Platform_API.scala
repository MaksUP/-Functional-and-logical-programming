trait Platform_API {

  def payroll(coach: Coach): Double = {
    var salary: Double = coach.ListStudents.length * coach.CoursePrice

    coach.Balance.MyTokens(coach.Balance.MyTokens + salary)
    return salary
  }

  def accrueScholarship(student: Student, coursePrice: Int): Double = {
    var scholarship: Double = 0.0
    var grade = student.Grades.last

    grade match {
      case 5 => scholarship = 1.1 * coursePrice
        student.Balance.MyTokens(student.Balance.MyTokens + scholarship)
      case 4 => scholarship = 1.0 * coursePrice
        student.Balance.MyTokens(student.Balance.MyTokens + scholarship)
      case 3 => scholarship = 0.9 * coursePrice
        student.Balance.MyTokens(student.Balance.MyTokens + scholarship)
      case 2 => scholarship = 0.8 * coursePrice
        student.Balance.MyTokens(student.Balance.MyTokens + scholarship)
      case 1 => scholarship = 0.7 * coursePrice
        student.Balance.MyTokens(student.Balance.MyTokens + scholarship)
      case _ => println("Invalid grade")
    }
    return scholarship
  }
}
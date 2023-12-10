abstract class Human(var name: String, var surname: String, var age: Int, var addr: Address, var balance:Balance) {
  private var _name: String = name
  private var _surname: String = surname
  private var _age: Int = age
  private var _addr: Address = addr
  private var _balance: Balance = balance

  override def toString(): String = s"${_name}, ${_surname}, ${_age}, ${_addr.toString()}, ${_balance.toString()}"
  def printHuman(): String = s"Name: ${_name} \nSurname: ${_surname} \nAge: ${_age} \n" + _addr.printAddress() + _balance.printBalance()

  def Name: String = _name
  def Name(newValue: String) = {_name = newValue}

  def Surname: String = _surname
  def Surname(newValue: String) = {_surname = newValue}

  def Age: Int = _age
  def Age(newValue: Int) = {
    if (newValue > 0) {_age = newValue}
    else println("Age cannot be negative \n")
  }

  def Address: Address = _addr
  def Address(newValue: Address) = {_addr = newValue}

  def Balance: Balance = _balance
  def Balance(newValue: Balance) = {_balance = newValue}
}
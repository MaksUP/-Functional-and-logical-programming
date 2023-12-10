class Balance(var myTokens: Double, var myMoney: Double) {
  private var _myTokens: Double = myTokens
  private var _myMoney: Double = myMoney

  override def toString(): String = s"${_myTokens}, ${_myMoney}"
  def printBalance(): String = s"Tokens: ${_myTokens} \nMoney: ${_myMoney} \n"

  def MyTokens: Double = _myTokens
  def MyTokens(newValue: Double) = {
    if (newValue >= 0) {_myTokens = Main.roundNumber(newValue, 2)}
    else println("Tokens cannot be negative \n")
  }

  def MyMoney: Double = _myMoney
  def MyMoney(newValue: Double) = {
    if (newValue >= 0) {_myMoney = Main.roundNumber(newValue, 2)}
    else println("Money cannot be negative \n")
  }
}
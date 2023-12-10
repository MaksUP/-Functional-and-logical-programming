object Exchange {
  private var tokens: Double = 1_000_000.0
  private var money: Double = 1_000_000.0
  private var purchasedTokens: Double = 0.0
  private var soldTokens: Double = 0.0
  private var tokenPrice: Double = money / tokens //purchasedTokens / soldTokens

  if (tokenPrice.isNaN || tokenPrice.isInfinity || tokenPrice == 0) {tokenPrice = 1.0}

  override def toString(): String = s"${tokens}, ${money}, ${purchasedTokens}, ${soldTokens}, ${tokenPrice}"
  def printExchange(): String = s"Tokens: ${tokens} \nMoney: ${money} \nPurchased tokens: ${purchasedTokens} " +
                                s"\nSold tokens: ${soldTokens} \nToken price: ${tokenPrice} \n"

  def Tokens: Double = tokens
  def Tokens(newValue: Double) = {
    if (newValue >= 0) {tokens = Main.roundNumber(newValue, 2)}
    else println("Tokens cannot be negative \n")
  }

  def Money: Double = money
  def Money(newValue: Double) = {
    if (newValue >= 0) {money = Main.roundNumber(newValue, 2)}
    else println("Money cannot be negative \n")
  }

  def PurchasedTokens: Double = purchasedTokens
  def PurchasedTokens(newValue: Double) = {
    if (newValue >= 0) {purchasedTokens = newValue}
    else println("Purchased tokens cannot be negative \n")
  }

  def SoldTokens: Double = soldTokens
  def SoldTokens(newValue: Double) = {
    if (newValue >= 0) {soldTokens = newValue}
    else println("Sold tokens cannot be negative \n")
  }

  def TokenPrice: Double = tokenPrice
  def TokenPrice(newValue: Double) = {
    if (newValue.isNaN || newValue.isInfinity || newValue == 0) {
      tokenPrice = 1.0
    } else if (newValue > 0) {
      tokenPrice = Main.roundNumber(newValue, 5)
    } else {
      println("Token price cannot be negative \n")
    }
  }
}
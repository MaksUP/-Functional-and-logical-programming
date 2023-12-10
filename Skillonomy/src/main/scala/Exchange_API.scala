trait Exchange_API {

  def buyTokens(person: Human, tokensForPurchase: Double): Boolean = {
    if (person.Balance.MyMoney >= tokensForPurchase * Exchange.TokenPrice) {

      if (Exchange.Tokens >= tokensForPurchase) {

        person.Balance.MyMoney(person.Balance.MyMoney - tokensForPurchase * Exchange.TokenPrice)
        person.Balance.MyTokens(person.Balance.MyTokens + tokensForPurchase)

        Exchange.PurchasedTokens(Exchange.PurchasedTokens + tokensForPurchase)
        Exchange.Tokens(Exchange.Tokens - tokensForPurchase)
        Exchange.Money(Exchange.Money + tokensForPurchase * Exchange.TokenPrice)
        Exchange.TokenPrice(Exchange.Money / Exchange.Tokens)
        println(Exchange.TokenPrice)

//        println("The purchase was successful")
        return true
      }
      else { println("Not enough tokens on the exchange"); return false }
    }
    else { println("Not enough money to buy tokens"); return false }
  }

  def sellTokens(person: Human, tokensForSale: Double): Boolean = {
    if (Exchange.Money >= tokensForSale * Exchange.TokenPrice) {

      person.Balance.MyTokens(person.Balance.MyTokens - tokensForSale)
      person.Balance.MyMoney(person.Balance.MyMoney + tokensForSale * Exchange.TokenPrice)

      Exchange.SoldTokens(Exchange.SoldTokens + tokensForSale)
      Exchange.Tokens(Exchange.Tokens + tokensForSale)
      Exchange.Money(Exchange.Money - tokensForSale * Exchange.TokenPrice)
      Exchange.TokenPrice(Exchange.Money / Exchange.Tokens)
      println(Exchange.TokenPrice)

//      println("The sale was successful")
      return true
    }
    else { /*println("Not enough money on the exchange");*/ return false }
  }
}
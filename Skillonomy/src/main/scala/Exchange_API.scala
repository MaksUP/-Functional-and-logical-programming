trait Exchange_API {

  def buyTokens(person: Human, tokensForPurchase: Double): Boolean = {
    if (person.Balance.MyMoney >= tokensForPurchase * Exchange.TokenPrice) {      // перевірка чи вистачить грошей для покупки токенів

      if (Exchange.Tokens >= tokensForPurchase) {       // перевірка чи вистачить токенів на біржі

        person.Balance.MyMoney(person.Balance.MyMoney - tokensForPurchase * Exchange.TokenPrice)    // оплата токенів
        person.Balance.MyTokens(person.Balance.MyTokens + tokensForPurchase)      // отримання токенів

        Exchange.PurchasedTokens(Exchange.PurchasedTokens + tokensForPurchase)    // оновлення загальної кількості куплених токенів на біржі
        Exchange.Tokens(Exchange.Tokens - tokensForPurchase)      // оновлення кількості токенів
        Exchange.Money(Exchange.Money + tokensForPurchase * Exchange.TokenPrice)      // оновлення кількості грошей
        Exchange.TokenPrice(Exchange.Money / Exchange.Tokens)     // оновлення ціни токену

//        println("The purchase was successful")
        return true
      }
      else { /*println("Not enough tokens on the exchange");*/ return false }
    }
    else { /*println("Not enough money to buy tokens");*/ return false }
  }

  def sellTokens(person: Human, tokensForSale: Double): Boolean = {
    if (Exchange.Money >= tokensForSale * Exchange.TokenPrice) {      // перевірка чи вистачить грошей на біржі

      person.Balance.MyTokens(person.Balance.MyTokens - tokensForSale)      // продаж токенів
      person.Balance.MyMoney(person.Balance.MyMoney + tokensForSale * Exchange.TokenPrice)      // отримання грошей

      Exchange.SoldTokens(Exchange.SoldTokens + tokensForSale)      // оновлення загальної кількості проданих токенів на біржі
      Exchange.Tokens(Exchange.Tokens + tokensForSale)
      Exchange.Money(Exchange.Money - tokensForSale * Exchange.TokenPrice)
      Exchange.TokenPrice(Exchange.Money / Exchange.Tokens)

//      println("The sale was successful")
      return true
    }
    else { /*println("Not enough money on the exchange");*/ return false }
  }
}
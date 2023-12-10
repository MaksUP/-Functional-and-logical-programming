class Address(var country:String, var city:String) {
  private var _country: String = country
  private var _city: String = city

  override def toString(): String = s"${_country}, ${_city}"
  def printAddress(): String = s"Country: ${_country} \nCity: ${_city} \n"

  def Country: String = _country
  def Country(newValue: String) = {_country = newValue}

  def City: String = _city
  def City(newValue: String) = {_city = newValue}
}
class Human(var name:String, var age:Int, var address: Address) {
	def showInfo():String = s"Name: $name \nAge: $age \nAddress: ${address.country}, ${address.city}"
}

class Address(var country:String, var city:String)


object Main {
	def main(args: Array[String]) = {

		var address = new Address("Ukraine", "Lviv")
		var human = new Human("Oleksandr", 25, address)
		println(human.showInfo())
	}
}
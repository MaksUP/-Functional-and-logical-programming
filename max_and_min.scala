object Hello {
	def main(args: Array[String]) = {
		
		val list = List(7, -1, 15, 22, 0, 4, 76, 12, -10, 39)

		var min = list.head
		var max = list.head

		for (num <- list) {
			if (num < min) {
				min = num
			}
			if (num > max) {
				max = num
			}
		}

		println(s"Max number: $max")
		println(s"Min number: $min")
	}
}
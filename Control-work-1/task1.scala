object Main {
	def main(args: Array[String]) = {
	
		var list = List(4, -8, 1, 9, 10, 3, -6, 18, 2, -5)
		FindMax(list)
		FindMin(list)
		Filter(list)
		FindElement(list)
		Exist(list)
		IsEmpty(list)
		HeadOfList(list)
		TailOfList(list)
		FindAverage(list)
		FindProduct(list)
		OutputList(list)
	}

	def FindMax(list:List[Int]) = {
		println("Max: " + list.max)
	}

	def FindMin(list:List[Int]) = {
		println("Min: " + list.min)
	}

	def Filter(list:List[Int]) = {
		println("Filter: " + list.filter(el=> el>0))
	}

	def FindElement(list:List[Int]) = {
		println("FindElement: " + list.find(el=> {el==9}))	
	}

	def Exist(list:List[Int]) = {
		println("Exist: " + list.exists(el=> {el/4 == 1}))
	}

	def IsEmpty(list:List[Int]) = {
		println("IsEmpty: " + list.isEmpty)
	}

	def HeadOfList(list:List[Int]) = {
		println("HeadOfList: " + list.head)
	}

	def TailOfList(list:List[Int]) = {
		println("TailOfList: " + list.tail)
	}

	def FindAverage(list:List[Int]) = {
		println("FindAverage: " + list.sum.toDouble/list.length)
	}

	def FindProduct(list:List[Int]) = {
		println("FindProduct: " + list.product)
	}

	def OutputList(list:List[Int]) = {
		println("OutputList: " + list)
	}
}
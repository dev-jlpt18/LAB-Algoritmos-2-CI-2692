fun main(args: Array<String>) {
	var n = args[0].toInt()
	var A = Array(n, {0})
	for (i in 0 until n) {
		A[i] = (0..n/3).random()
	}
	var pares = generarArregloPares(A)
	
	var TablaHashChaining = HashTableChaining()
	var tiempoChaining = medirTiempoChaining(pares, TablaHashChaining)
	println("La prueba de la tabla de Hash con encadenamiento tomó ${tiempoChaining} segundos")

	/*println("La tabla chaining es")
	println(TablaHashChaining.toString())
	println("Y las claves conocidas son")
	println(TablaHashChaining.conocidas.toString())*/
	
	var TablaHashCuckoo = CuckooHashTable()
	var tiempoCuckoo = medirTiempoCuckoo(pares, TablaHashCuckoo)
	println("La prueba de la tabla de Hash cuckoo tomó ${tiempoCuckoo} segundos")

	/*println("La tabla cuckoo es")
	println(TablaHashCuckoo.toString())
	println("Y las claves conocidas son")
	println(TablaHashCuckoo.conocidas.toString())*/
}

fun generarArregloPares(A: Array<Int>): Array<Pair<Int,String>>{
	var n = A.size
	var B = Array(n, {Pair(0, "0")})
	for (i in 0 until n) {
		B[i] = Pair(A[i], A[i].toString())
	}
	return B
}

fun medirTiempoChaining(A: Array<Pair<Int,String>>, TablaHash: HashTableChaining): Double {
	var tInicio: Long = System.currentTimeMillis()
	probarTablaHashChaining(A, TablaHash)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun probarTablaHashChaining(A: Array<Pair<Int,String>>, TablaHash: HashTableChaining) {
	var n = A.size
	for (i in 0 until n) {
		if (TablaHash.buscar(A[i].first) == null){
			TablaHash.agregar(A[i].first, A[i].second)
			
			/*println("Agregar ${A[i].first}. La tabla queda")
			println(TablaHash.toString())
			println("Y las claves conocidas son")
			println(TablaHash.conocidas.toString())
			println("")*/
		
		} else {
			TablaHash.eliminar(A[i].first)
			
			/*println("eliminar ${A[i].first}. La tabla queda")
			println(TablaHash.toString())
			println("Y las claves conocidas son")
			println(TablaHash.conocidas.toString())
			println("")*/
		
		}
	}
}

fun medirTiempoCuckoo(A: Array<Pair<Int,String>>, TablaHash: CuckooHashTable): Double {
	var tInicio: Long = System.currentTimeMillis()
	probarTablaHashCuckoo(A, TablaHash)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun probarTablaHashCuckoo(A: Array<Pair<Int,String>>, TablaHash: CuckooHashTable) {
	var n = A.size
	for (i in 0 until n) {
		if (TablaHash.buscar(A[i].first) == null){
			TablaHash.agregar(A[i].first, A[i].second)
			
			/*println("Agregar ${A[i].first}. La tabla queda")
			println(TablaHash.toString())
			println("Y las claves conocidas son")
			println(TablaHash.conocidas.toString())
			println("")*/
		
		} else {
			TablaHash.eliminar(A[i].first)
			
			/*println("eliminar ${A[i].first}. La tabla queda")
			println(TablaHash.toString())
			println("Y las claves conocidas son")
			println(TablaHash.conocidas.toString())
			println("")*/
		
		}
	}
}


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
	println(" ")
	var TablaHashCuckoo = CuckooHashTable()
	var tiempoCuckoo = medirTiempoCuckoo(pares, TablaHashCuckoo)
	println("La prueba de la tabla de Hash con cuckoo hashing tomó ${tiempoCuckoo} segundos")
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
		} else {
			TablaHash.eliminar(A[i].first)
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
		} else {
			TablaHash.eliminar(A[i].first)
		}
	}
}


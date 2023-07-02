fun main(args: Array<String>) {
    var n = args[0].toInt()
    var secuencia: Array<Pair<Int, String>> = Array(n){Pair(0,"")}
    var x: Int
    var y: String
    for (i in 0 until n) {
        x = (0..(n/3)).random()
        y = x.toString()
        secuencia[i] = Pair(x,y)
    }
    var TablaHashChaining = HashTableChaining()
	var tiempoChaining = medirTiempoChaining(secuencia, TablaHashChaining)
	println("La prueba de la tabla de Hash con encadenamiento tomó ${tiempoChaining} segundos")

	/*println("La tabla chaining es")
	println(TablaHashChaining.toString())
	println("Y las claves conocidas son")
	println(TablaHashChaining.conocidas.toString())*/
	
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

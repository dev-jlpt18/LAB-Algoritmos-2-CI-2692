fun main() {
	var n = 1000000
	var secuencia = Array<Int>(n,{0})
	for (i in 0 until n) {
		secuencia[i] = (0..900000).random()
	}
	for (j in 1 until 6) {
		for (i in 10..100 step 10) {
			var tiempo = medirTiempo(secuencia,i)
			println("Prueba ${j}. Tamaño del arreglo: ${i}. Tiempo del procedimiento: ${tiempo} segundos")
		}
	}
}

fun merge(U: Array<Int>, V: Array<Int>, T: Array<Int>) {
	var i = 0
	var j = 0
	U[(U.size-1)] = Int.MAX_VALUE // Centinela, garantiza que no haya errores de rango al evaluar los condicionales
	V[(V.size-1)] = Int.MAX_VALUE // Centinela, garantiza que no haya errores de rango al evaluar los condicionales
	for (k in 0 until (U.size+V.size-2)) {
		if (U[i] <= V[j]) {
			T[k] = U[i]
			i = i+1
		} else {
			T[k] = V[j]
			j = j+1
		}
	}
}

fun mergesortInsertionPrueba(T: Array<Int>, n: Int) {	
	if(T.size <= n){
		insertionSort(T)
	} else {
		var k = T.size/2
		var m = T.size/2
		if (T.size%2 != 0){
			m = T.size/2 + 1
		}
		var U = Array<Int>((1+k), {0})
		var V = Array<Int>((1+m), {0})
		for (i in 0 until k) {
			U[i] = T[i]
		}
		// Centinela, garantiza que al ordenar U en la llamada recursiva los primeros T.size elementos serán del arreglo T
		U[k] = Int.MAX_VALUE-1 
		for (i in 0 until m) {
			V[i] = T[i+k]
		}
		// Centinela, garantiza que al ordenar V en la llamada recursiva los primeros T.size elementos serán del arreglo T
		V[m] = Int.MAX_VALUE-1
		mergesortInsertionPrueba(U,n)
		mergesortInsertionPrueba(V,n)
		merge(U,V,T)
	}
}

fun medirTiempo(T: Array<Int>, n: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	mergesortInsertionPrueba(T,n)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}
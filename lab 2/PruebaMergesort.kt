fun main() {
	var secuencia = Array<Int>(1000000,{0})
	for (i in 0 until secuencia.size) {
		secuencia[i] = (0..900000).random()
	}
	for (j in 1 until 6) {
		for (i in 10..100 step 10) {
			var tiempo = medirTiempo(secuencia,i)
			println("Prueba ${j}. Tamaño del arreglo: ${i}. Tiempo del procedimiento: ${tiempo} segundos")
			var tiempo2 = medirTiempo2(secuencia,i)
			println("Prueba ${j}, v2. Tamaño del arreglo: ${i}. Tiempo del procedimiento: ${tiempo2} segundos")
		}
	}
}

fun merge2(U: Array<Int>, V: Array<Int>, T: Array<Int>) {
	var i = 0
	var j = 0
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
		if (T.size%2 == 1){
			m = T.size/2 + 1
		}
		var U = Array<Int>((1+k), {0})
		var V = Array<Int>((1+m), {0})
		for (i in 0 until k) {
			U[i] = T[i]
		}
		for (i in 0 until m) {
			V[i] = T[i+k]
		}
		mergesortInsertionPrueba(U,n)
		mergesortInsertionPrueba(V,n)
		merge(U,V,T)
	}
}

fun mergesortInsertionPrueba2(T: Array<Int>, n: Int) {	
	if(T.size <= n){
		insertionSort(T)
	} else {
		var k = T.size/2
		var m = T.size/2
		if (T.size%2 != 0){
			m = T.size/2 + 1
		}
		var U = Array<Int>(k, {0})
		var V = Array<Int>(m, {0})
		for (i in 0 until k) {
			U[i] = T[i]
		}
		for (i in 0 until m) {
			V[i] = T[i+k]
		}
		mergesortInsertionPrueba2(U,n)
		mergesortInsertionPrueba2(V,n)
		U = U.plus(Int.MAX_VALUE)
		V = V.plus(Int.MAX_VALUE)
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

fun medirTiempo2(T: Array<Int>, n: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	mergesortInsertionPrueba2(T,n)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	if (!estaEnOrdenAscendente(T)) {
		return -1.0
    }
	return tiempo
}

fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}

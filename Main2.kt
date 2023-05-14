fun generarSecuencia(n: Int, clase: String): Array<Int> {
	var secuencia: Array<Int> = Array(n, {-1})
	if (clase.equals("random")) {
		for (i in 0 until n) {
			secuencia[i] = (0..n).random()
		}
	} else if (clase.equals("sorted")) {
		for (i in 0 until n) {
			secuencia[i] = i + 1
		}
	} else if (clase.equals("inv")) {
		for (i in 0 until n) {
			secuencia[i] = n - i
		}
	} else if (clase.equals("zu")) {
		for (i in 0 until n) {
			secuencia[i] = (0..1).random()
		}
	} else if (clase.equals("media")) {
		for (i in 0 until n) {
			if (i < n/2) {
				secuencia[i] = i + 1
			} else {
				secuencia[i] = n - i 
			}
		}
	}
	return secuencia
}

fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    val n = secuencia.size
    for (i in 0 until n-1) {
		if (secuencia [i] > secuencia [i+1]) {
	    	return false
		} 
    }
    return true
}

fun obtenerPromedioSecuencia(A: Array<Double>): Double {
	var suma: Double = 0.0
	for (i in 0 until A.size){
		suma = suma + A[i]
	}
	var n: Int = A.size
	var promedio: Double = suma/n
	return promedio 
}

fun obtenerDesviacionEstandarSecuencia(A: Array<Double>): Double {
	var promedio: Double = obtenerPromedioSecuencia(A)
	var suma: Double = 0.0
	for (i in 0 until A.size){
		suma = suma + Math.pow((A[i] - promedio),2.0)
	}
	var n: Int = A.size
	var varianza: Double = suma/(n-1)
	var desviacionEstandar: Double = Math.sqrt(varianza)
	return desviacionEstandar

}

fun medirTiempoInsertionSort(A: Array<Int>): Double {
	var tInicio: Long = System.currentTimeMillis()
	insertionSort(A)
	if (!estaEnOrdenAscendente(A)) {
		return -1.0
    }
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun eficienciaInsertionSort(A: Array<Int>, intentos: Int): Int {
	if(intentos > 1){
		var tiempos: Array<Double> = Array(intentos, {0.0})
		for (i in 0 until intentos) {
			tiempos[i] = medirTiempoInsertionSort(A)
			if (tiempos[i] == -1.0) {
				println("Insertion Sort: error intento ${i}, la secuencia no está ordenada")
				return -1
			}
		}
		val tiempoPromedio: Double = obtenerPromedioSecuencia(tiempos)
		val desviacionEstandar: Double = obtenerDesviacionEstandarSecuencia(tiempos)
		println("Algoritmo: Insertion Sort. Tiempo promedio: ${tiempoPromedio}, desviación estándar: ${desviacionEstandar}")
    	println("¡Todos los ordenamientos fueron exitosos!")
    	return 1
    } else if (intentos == 1){
    	var tiempo: Double = medirTiempoInsertionSort(A)
    	println("Algoritmo: Insertion Sort. Tiempo: ${tiempo}")
    	println("¡Ordenamiento exitoso!")
    	return 1
    } else {
    	return -2
    }
}

fun main (args: Array<String>) {
	if(args.size != 6){
		println("Error, falta un argumento")
		return
	}
	var n: Int = 0
	var t: Int = 0
	var s: String = "S"
	for (i in 0 until (args.size -1)) {
		when(args[i]){
			"-t" -> t = args[i+1].toInt()
			"-s" -> s = args[i+1]
			"n" -> n = args[i+1].toInt() 
		}
	}
	var secuencia: Array<Int> = generarSecuencia(n,s)
	if(secuencia[0] == -1){
		println("Error, la clase de secuencia indicada no existe")
		return
	}

	var eficiencia: Int = eficienciaInsertionSort(secuencia, t)
	if (eficiencia == -1) {
		return
	} else if(eficiencia == -2) {
		println("Error, número de intentos no válido")
	}

}
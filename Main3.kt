fun main(args: Array<String>) {
    if(args.size != 6){
		println("Error, falta un argumento")
		return
	}
    var t: Int = 0
    var s: String = ""
    var n: Int = 0
    for (i in 0 until args.size) {
        when (args[i]) {
            "-t" -> t = args[i+1].toInt()
            "-n" -> n = args[i+1].toInt()
            "-s" -> s = args[i+1]
        }
    }
    if (t < 1) {
        println("Error, número de intentos no válido")
        return
    }
    if (n < 1) {
        println("Error, tamaño de la secuencia no válido")
        return
    }
    var secuencia: Array<Int> = generadorDeSecuencias(s, n)
    // println("La secuencia es: " + secuencia.contentToString()) 
    if(secuencia[0] == -1){
		println("Error, la clase de secuencia indicada no existe")
		return
	}
    println("Tenemos que se realizarán ${t} intentos, la secuencia es de clase ${s} y contiene ${n} elementos")
    for (i in 1 until 5) {
        var check = eficiencia(secuencia, t, i)
        if (check == -1) {
            return
        } else if(check == -2) {
            println("Error, número de intentos no válido")
        }
    }
}

fun generadorDeSecuencias(id: String, tamaño: Int): Array<Int> {
    var secuencia = Array<Int>(tamaño,{-1})
    if (id == "random") {
        for (i in 0 until tamaño) {
            secuencia[i] = (0..tamaño).random()
        }
        return secuencia
    } else if (id == "sorted") {
        for (i in 0 until tamaño) {
            secuencia[i] = i+1
        }
        return secuencia
    } else if (id == "inv") {
        for (i in 0 until tamaño) {
            secuencia[i] = tamaño-i
        }
        return secuencia
    } else if (id == "zu") {
        for (i in 0 until tamaño) {
            secuencia[i] = (0..1).random()
        }
        return secuencia
    } else if (id == "media") {
        for (i in 0 until tamaño) {
            if (i < tamaño/2) {
                secuencia[i] = i+1 
            } else {
                secuencia[i] = tamaño-i
            }
        }
        return secuencia
    } else {
        return secuencia
    }
}

fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
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

fun obtenerSort(tipo: Int): String {
    when (tipo){
        1 -> return "InsertionSort"
        2 -> return "BubbleSort"
        3 -> return "ShellSort"
        4 -> return "SelectionSort"
    }
    return "null"
}


fun medirTiempo(A: Array<Int>, x: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	when (x){
        1 -> insertionSort(A)
        2 -> bubbleSort(A)
        3 -> shellSort(A)
        4 -> selectionSort(A)
    }
	if (!estaEnOrdenAscendente(A)) {
		return -1.0
    }
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
    // println("Insertion Sort: " + A.contentToString())
	return tiempo
}

fun eficiencia(A: Array<Int>, intentos: Int, algoritmo: Int): Int {
	var id: String = obtenerSort(algoritmo)
    if(intentos > 1){
		var tiempos: Array<Double> = Array(intentos, {0.0})
		for (i in 0 until intentos) {
			tiempos[i] = medirTiempo(A, algoritmo)
			if (tiempos[i] == -1.0) {
				println("${id}: error intento ${i}, la secuencia no está ordenada")
				return -1
			}
		}
		var tiempoPromedio: Double = obtenerPromedioSecuencia(tiempos)
		var desviacionEstandar: Double = obtenerDesviacionEstandarSecuencia(tiempos)
		println("Algoritmo: ${id}.")
        println("Tiempo promedio: ${tiempoPromedio} segundos")
        println("Desviación estándar: ${desviacionEstandar} segundos")
    	println("¡Todos los ordenamientos fueron exitosos!")
    	return 1
    } else {
    	var tiempo: Double = medirTiempo(A, algoritmo)
        if (tiempo == -1.0) {
            println("La secuencia no esta ordenada")
            return -1
        } else {
            println("Algoritmo: ${id}. Tiempo: ${tiempo} segundos")
    	    println("¡Ordenamiento exitoso!")
            return 1  
        }
    } 
}
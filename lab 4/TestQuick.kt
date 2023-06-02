/**
 * Main corresponde a un programa cliente para ejecutar los algoritmos de ordenamiento dados en
 * la librería Sortlib bajo una misma serie de pruebas, en las cuales el usuario determina
 * la clase de la secuencia a ordenar, su tamaño y el número de intentos a realizar.
 * 
 * Desarrollado por: Castillo, Haydeé y Prieto, Jesús. 
 */

// Función main. Dicha función corresponde al punto de entrada del programa cliente
fun main(args: Array<String>) {
    // Verificar si la entrada de datos está completa    
    if (args.size != 4){
		println("Error, falta un argumento")
		return
	}
    // Asignación de datos a las variables
    var t: Int = 0
    var s: String = ""
    var n: Int = 0
    for (i in 0 until args.size) {
        when (args[i]) {
            "-n" -> n = args[i+1].toInt()
            "-s" -> s = args[i+1]
        }
    }
    // Verificar si los datos (intentos y cantidad de elementos) son válidos
    if (t < 1) {
        println("Error, número de intentos no válido")
        return
    }
    if (n < 1) {
        println("Error, tamaño de la secuencia no válido")
        return
    }
    // Generación de la secuencia y verificación de existencia de la clase de secuencia
    var secuencia: Array<Int> = generadorDeSecuecias(s, n)
    if(secuencia[0] == -1){
		println("Error, la clase de secuencia indicada no existe")
		return
	}
    println("Tenemos que se realizarán ${t} intentos, la secuencia es de clase ${s} y contiene ${n} elementos")
    println("")
    // Chequeo de la eficiencia de cada algoritmo Sort
    for (i in 1 until 4) {
        var check = eficiencia(s, n, i)
        if (check == -1) {
            return
        }
    }
}

// Función que genera la secuencia dependiendo de la clase indicada 
fun generadorDeSecuecias(id: String, tamaño: Int): Array<Int> {
    var secuencia = Array<Int>(tamaño,{-1})
    if (id == "random") {
        // Genera una secuencia de N elementos, de manera aleatoria
        for (i in 0 until tamaño) {
            secuencia[i] = (0..tamaño).random()
        }
        return secuencia
    } else if (id == "sorted") {
        // Genera una secuencia de N elementos, con la forma [1,2,...N]
        for (i in 0 until tamaño) {
            secuencia[i] = i+1
        }
        return secuencia
    } else if (id == "inv") {
        // Genera una secuencia de N elementos, con la forma [N...,2,1]
        for (i in 0 until tamaño) {
            secuencia[i] = tamaño-i
        }
        return secuencia
    } else if (id == "zu") {
        // Genera una secuencia de N elementos de manera aleatoria, usando sólo 1 y 0
        for (i in 0 until tamaño) {
            secuencia[i] = (0..1).random()
        }
        return secuencia
    } else if (id == "media") {
        // Genera una secuencia de N elementos, con la forma [1,2,...[N/2],[N/2]...,2,1]
        for (i in 0 until tamaño) {
            if (i < tamaño/2) {
                secuencia[i] = i+1 
            } else {
                secuencia[i] = tamaño-i
            }
        }
        return secuencia
    } else {
        // Genera una secuencia de N elementos, con solo (-1) como elementos
        return secuencia
    }
}

// Función que verifica si la secuencia esta ordenado de manera ascendente
fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}

// Funcion que obtiene el promedio de una secuencia de números
fun obtenerPromedioSecuencia(A: Array<Double>): Double {
	var suma: Double = 0.0
	for (i in 0 until A.size){
		suma = suma + A[i]
	} 
	var n: Int = A.size
	var promedio: Double = suma/n
	return promedio 
}

// Función que obtiene la derivación estandar de una secuencia de números
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

// Función que determina cuál algoritmo Sort se usara
fun obtenerAlgoritmoSort(tipo: Int): String {
    when (tipo){
        1 -> return "QuicksortClasico"
        2 -> return "QuicksortThreeWay"
        3 -> return "QuicksortDualPivot"
    }
    return "Algoritmo no encontrado"
}

// Función que mide el tiempo (en segundos) del algoritmo Sort especificado
fun medirTiempo(A: Array<Int>, x: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	when (x){
        1 -> quicksortClasico(A)
        2 -> quicksortThreeWay(A)
        3 -> quicksortDualPivot(A) 
    }
	if (!estaEnOrdenAscendente(A)) {
        // Si la secuencia no está ordenada de forma ascendente, la función retorna -1
		return -1.0
    }
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

// Función que determina la eficiencia del algoritmo Sort seleccionado, de acuerdo al tiempo y el número de intentos 
fun eficiencia(s: String, n: Int, algoritmo: Int): Int {
    var j = 0
    var id: String = obtenerAlgoritmoSort(algoritmo)
    var tiempos: Array<Double> = Array(10, {0.0})
    while (j < 10) {
        var A: Array<Int> = generadorDeSecuecias(s, n)
		tiempos[j] = medirTiempo(A, algoritmo)
		if (tiempos[j] == -1.0) {
			println("${id}: error intento ${j}, la secuencia no está ordenada")
			return -1
		}
        j++
    }
	val tiempoPromedio: Double = obtenerPromedioSecuencia(tiempos)
	val desviacionEstandar: Double = obtenerDesviacionEstandarSecuencia(tiempos)
	println("Algoritmo: ${id}.")
    println("Tiempo promedio: ${tiempoPromedio} segundos")
    println("Desviación estándar: ${desviacionEstandar} segundos")
    println("¡Todos los ordenamientos fueron exitosos!")
    println("")
    return 1
    
}
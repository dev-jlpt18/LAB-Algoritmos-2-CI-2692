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
    if (args.size != 6){
		println("Error, falta un argumento")
		return
	}
    // Asignación de datos a las variables
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
    for (i in 1 until 5) {
        var check = eficiencia(secuencia, t, i)
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
        1 -> return "InsertionSort"
        2 -> return "BubbleSort"
        3 -> return "ShellSort"
        4 -> return "SelectionSort"
    }
    return "Algoritmo no encontrado"
}

// Función que mide el tiempo (en segundos) del algoritmo Sort especificado
fun medirTiempo(A: Array<Int>, x: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	when (x){
        1 -> insertionSort(A)
        2 -> bubbleSort(A)
        3 -> shellSort(A)
        4 -> selectionSort(A)
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
fun eficiencia(A: Array<Int>, intentos: Int, algoritmo: Int): Int {
	var id: String = obtenerAlgoritmoSort(algoritmo)
    if(intentos > 1) {
		var tiempos: Array<Double> = Array(intentos, {0.0})
		for (i in 0 until intentos) {
			tiempos[i] = medirTiempo(A, algoritmo)
			if (tiempos[i] == -1.0) {
				println("${id}: error intento ${i}, la secuencia no está ordenada")
				return -1
			}
		}
		val tiempoPromedio: Double = obtenerPromedioSecuencia(tiempos)
		val desviacionEstandar: Double = obtenerDesviacionEstandarSecuencia(tiempos)
		println("Algoritmo: ${id}.")
        println("Tiempo promedio: ${tiempoPromedio} segundos")
        println("Desviación estándar: ${desviacionEstandar} segundos")
    	println("¡Todos los ordenamientos fueron exitosos!")
        println("")
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
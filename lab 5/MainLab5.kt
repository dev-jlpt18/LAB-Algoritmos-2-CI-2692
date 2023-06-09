fun main(args: Array<String>) {
    var size = args[0].toInt()
    eficiencia(1000000, size)

}

fun obtenerSecuenciaAleatoria(rango: Int, size: Int): Array<Int> {
    var A = Array(size, {0})
    for (i in 0 until size) {
        A[i] = (0 until rango).random()
    }
    return A
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
	var n = A.size
	var promedio = suma/n
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

// Función que determina cuál algoritmo Sort se usara
fun obtenerAlgoritmoSort(tipo: Int): String {
    when (tipo){
        1 -> return "mergesortInsertion"
        2 -> return "heapsort"
        3 -> return "smoothsort"
        4 -> return "quicksortClasico"
        5 -> return "quicksortThreeWay"
        6 -> return "quicksortDualPivot"
        7 -> return "countingSort"
        8 -> return "radixSort"
    }
    return "Algoritmo no encontrado"
}

// Función que mide el tiempo (en segundos) del algoritmo Sort especificado
fun medirTiempo(A: Array<Int>, x: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	when (x){
        1 -> mergesortInsertion(A)
        2 -> heapsort(A)
        3 -> smoothsort(A)
        4 -> quicksortClasico(A)
        5 -> quicksortThreeWay(A)
        6 -> quicksortDualPivot(A)
        7 -> countingSort(A)
        8 -> radixSort(A)
    }
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
    if (!estaEnOrdenAscendente(A)) {
        return -1.0
    }
	return tiempo
}

fun eficiencia(rangoSecuencia: Int, actualSize: Int) {
    var tiempos: Array<Double> = Array(3, {0.0})
    for(j in 1 until 9) {
        var id = obtenerAlgoritmoSort(j)
        for (i in 0 until 3) {
            var A = obtenerSecuenciaAleatoria(rangoSecuencia, actualSize)
            tiempos[i] = medirTiempo(A, j) 
            if (tiempos[i] == -1.0) {
                println("${id}: error arreglo ${i}, la secuencia no está ordenada")
                return
            }
        }
        var tiempoPromedio = obtenerPromedioSecuencia(tiempos)
        var desviacionEstandar = obtenerDesviacionEstandarSecuencia(tiempos)
        println("Algoritmo: ${id}. Tamaño del arreglo: ${actualSize}. Arreglos: 3")
        println("Tiempo promedio: ${tiempoPromedio} segundos")
        println("Desviación estándar: ${desviacionEstandar} segundos")
        println("¡Todos los ordenamientos fueron exitosos!")
        println("")
    }
}
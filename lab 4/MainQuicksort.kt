fun main() {
    eficiencia(500000)
    eficiencia(1000000)
    eficiencia(1500000)
    eficiencia(2000000)

}

fun obtenerSecuenciaAleatoria(N: Int): Array<Int> {
    var A = Array(N, {0})
    for (i in 0 until N) {
        A[i] = (0..N).random()
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

// Función que determina cuál algoritmo Sort se usara
fun obtenerAlgoritmoQuicksort(tipo: Int): String {
    when (tipo){
        1 -> return "quicksortClasico"
        2 -> return "quicksortThreeWay"
        3 -> return "quicksortDualPivot"
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
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
    if (!estaEnOrdenAscendente(A)) {
        return -1.0
    }
	return tiempo
}

fun eficiencia(actualSize: Int) {
    var tiempos: Array<Double> = Array(10, {0.0})
    for(j in 1 until 4) {
        var id = obtenerAlgoritmoQuicksort(j)
        for (i in 0 until 10) {
            var A = obtenerSecuenciaAleatoria(actualSize)
            tiempos[i] = medirTiempo(A, j) 
            if (tiempos[i] == -1.0) {
                println("${id}: error arreglo ${i}, la secuencia no está ordenada")
                return
            }
        }
        val tiempoPromedio: Double = obtenerPromedioSecuencia(tiempos)
        val desviacionEstandar: Double = obtenerDesviacionEstandarSecuencia(tiempos)
        println("Algoritmo: ${id}. Tamaño del arreglo: ${actualSize}")
        println("Tiempo promedio: ${tiempoPromedio} segundos")
        println("Desviación estándar: ${desviacionEstandar} segundos")
        println("¡Todos los ordenamientos fueron exitosos!")
        println("")
    }
}
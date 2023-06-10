import kotlin.math.max

fun countingSort(A: Array<Int>) {
	var n = A.size
	var k = A.max()
	var B = Array(n, {0})
	countingSortOriginal(A,B,k)
	for (i in 0 until n) {
		A[i] = B[i]
	}
}

fun countingSortOriginal(A: Array<Int>, B: Array<Int>, k: Int) {
	var n = A.size
	var C = Array(k+1,{0})
	for (j in 0 until n) {
		C[A[j]] = C[A[j]] + 1
	}
	for (i in 1 until k+1) {
		C[i] = C[i] + C[i-1]
	}
	for (j in n-1 downTo 0) {
		B[C[A[j]]-1] = A[j]
		C[A[j]] = C[A[j]] - 1
	}
}

fun radixSort(A: Array<Int>) {
	var n = A.size
	var k = A.max()
	var d = obtenerNumeroDigitos(k)
	var potenciaDe10 = 1
	for (i in 0 until d+1) {
		var B = Array(n, {0})
		countingSortDigitos(A,B,9,potenciaDe10)
		potenciaDe10 = 10*potenciaDe10
		for (j in 0 until n) {
			A[j] = B[j]
		}
	}
}

fun obtenerNumeroDigitos(a: Int): Int {
	if (a == 0) {
		return 1
	}
	var potenciaDe10 = 10
	var numeroDeDigitos = 1
	while(a/potenciaDe10 > 0) {
		potenciaDe10 = 10*potenciaDe10
		numeroDeDigitos = numeroDeDigitos + 1
	}
	return numeroDeDigitos
}

fun countingSortDigitos(A: Array<Int>, B: Array<Int>, k: Int, potenciaDe10: Int) {
	var n = A.size
	var C = Array(k+1,{0})
	for (j in 0 until n) {
		var digito = (A[j]/potenciaDe10)%10
		C[digito] = C[digito] + 1
	}
	for (i in 1 until k+1) {
		C[i] = C[i] + C[i-1]
	}
	for (j in n-1 downTo 0) {
		var digito = (A[j]/potenciaDe10)%10
		B[C[digito]-1] = A[j]
		C[digito] = C[digito] - 1
	}
}

// PARA PROBAR LOS ALGORITMOS

/*
fun main(args: Array<String>) {
	var n = args[0].toInt()
	var A = Array(n,{0})
	for (i in 0 until n) {
		A[i] = (0 until 1000000).random()
	}
	println("Se tiene un arreglo A de ${n} elementos")
	var tiempo = medirTiempo(A)
	println("Se aplica radixSort y el arreglo A queda")
	if (estaEnOrdenAscendente(A) == false) {
		println("No ordenado")
	} else {
		println("Ordenado")
	}
	println("En ${tiempo} segundos")
}

fun imprimiArreglo(A: Array<Int>) {
	for (i in 0 until A.size) {
		print("${A[i]} ")
	}
	println("")
}

fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}

fun medirTiempo(A: Array<Int>): Double {
	var tInicio: Long = System.currentTimeMillis()
	radixSort(A)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
    if (!estaEnOrdenAscendente(A)) {
        return -1.0
    }
	return tiempo
}
*/
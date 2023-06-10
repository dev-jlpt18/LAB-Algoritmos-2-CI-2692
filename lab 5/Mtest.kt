fun main() {
    var o = 10
    var A = arrayOf(1,9,78,5,456,135,465,23,46,87,4354,13,46,79,46,4,9463)
    var B = arrayOf(1,9,78,5,456,135,465,23,46,87,4354,13,46,79,46,4,9463,465465,57987,16546,1374,6546,2,46451,654,2165,41,354,61,354,2,654,3,16,54,21,65,745,7496,4,2)
    var C = arrayOf(1,56,48,13,79,54,12,79,1,56,4)
    var D = arrayOf(538,754,418,667,385,247,128,517,717,985,253,411,825,450,300)
    radixSort(D)

    println(D.contentToString())
    if (estaEnOrdenAscendente(D) == false) {
        println("Error")
    }else {
        println("Ordenado de manera satisfactoria")
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
/* 
fun radixSort(A: Array<Int>) {
	var n = A.size
	var k = obtenerMaximo(A)
	var d = obtenerNumeroDigitos(k)
	var potenciaDe10 = 1
	for (i in 0 until d+1) {
		var B = Array(n, {0})
		countingSortDigitos(A,B,9,potenciaDe10)
		potenciaDe10 = 10*potenciaDe10
		for (j in 0 until n) {
			A[j] = B[j]
		}
        println(A.contentToString())
        println(".....")
	}
}

fun obtenerNumeroDigitos(a: Int): Int {
	if (a == 0) {
		return 1
	}
	var potenciaDe10 = 10
	while(a/potenciaDe10 > 0) {
		potenciaDe10 = 10*potenciaDe10
	}
	var exponentePotenciaDe10 = Math.log(potenciaDe10.toDouble())/Math.log(10.0)
	var numeroDeDigitos = exponentePotenciaDe10.toInt()
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

fun obtenerMaximo(A: Array<Int>): Int {
	var n = A.size
	var maximo = A[0]
	for (i in 1 until n) {
		if (A[i] > maximo) {
			maximo = A[i]
		}
	}
	return maximo
}*/
fun heapsort(A: Array<Int>) {
	buildMaxHeap(A)
	var n = A.size
	for (i in (n-1) downTo 1) {
		swap(A, 0, i)
		maxHeapify(A,0,i-1)
	}
}

fun maxHeapify(A: Array<Int>, i: Int, n: Int) {
	var l = 2*i + 1
	var r = 2*i + 2
	var largest: Int
	if (l <= n && A[l] > A[i]) {
		largest = l
	} else {
		largest = i
	}
	if (r <= n && A[r] > A[largest]) {
		largest = r
	}
	if (largest != i) {
		swap(A,i,largest)
		maxHeapify(A,largest,n)
	}
}

fun buildMaxHeap(A: Array<Int>) {
	var n = A.size
	for (i in (n/2-1) downTo 0) {
		maxHeapify(A,i,n-1)
	}
}

// Lo que sigue es para probar el programa

fun main(args: Array<String>) {
	var N = args[0].toInt()
	var A = Array(N,{0})
	for (i in 0 until N) {
		A[i] = (0..900000).random()
	}
	println("Se tiene A")
	//imprimiArreglo(A)
	if(estaEnOrdenAscendente(A) == false) {
		println("Un arreglo desordenado")
	} else {
		println("Un arreglo ordenado")
	}
	var tiempo = medirTiempo(A)
	println("Se aplica Heapsort y se obtiene")
	//imprimirArreglo(A)
	if(estaEnOrdenAscendente(A) == false) {
		println("Un arreglo desordenado")
	} else {
		println("Un arreglo ordenado")
	}
	println("En ${tiempo} segundos")

}

fun swap (A: Array<Int>,x: Int, y: Int) {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
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
	heapsort(A)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun imprimiArreglo(A: Array<Int>) {
	for (i in 0 until A.size) {
		print("${A[i]} ")
	}
	println("")
}
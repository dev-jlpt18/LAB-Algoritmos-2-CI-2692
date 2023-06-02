// El Clásico

fun quicksortClasico(A: Array<Int>) {
	var p = 0
	var r = A.size - 1
	quicksortClasicoOriginal(A,p,r)
}

fun quicksortClasicoOriginal(A: Array<Int>, p: Int, r: Int) {
	if (p < r) {
		var q = particionClasico(A,p,r)
		quicksortClasicoOriginal(A, p, q-1)
		quicksortClasicoOriginal(A, q+1, r)
	}
} 

fun particionClasico(A: Array<Int>, p: Int, r: Int): Int {
	var x = A[r]
	var i = p - 1
	for (j in p until r) {
		if (A[j] <= x) {
			i = i + 1
			swap(A, i, j)
		}
	}
	swap(A, i+1, r)
	return i+1

}

// Three Way

fun quicksortThreeWay(A: Array<Int>) {
	var l = 0
	var r = A.size - 1
	quicksortThreeWayOriginal(A, l, r)
}

fun quicksortThreeWayOriginal(A: Array<Int>, l: Int, r: Int) {
	var i = l - 1
	var j = r 
	var p = l - 1
	var q = r
	if (r <= l) {
		return
	}
	var v = A[r]
	while (true) {
		while (A[++i] < v) {
		}
		while (v < A[--j]) {
			if (j == l) {
				break
			}
		}
		if (i >= j) {
			break
		}
		swap(A, i, j)
		if (A[i] == v)  {
			p++
			swap(A,p,i)
		}
		if (v == A[j]) {
			q--
			swap(A,j,q)
		}
	}
	swap(A,i,r)
	j = i - 1
	i = i + 1
	for(k in l until p) {
		swap(A,k,j)
		j = j - 1
		
	}
	for(k in (r-1) downTo q){
		swap(A,i,k)
		i = i + 1 
	}
	quicksortThreeWayOriginal(A, l, j)
	quicksortThreeWayOriginal(A, i, r)
}

// Dual Pivot

fun quicksortDualPivot(A: Array<Int>) {
	var left = 0
	var right = A.size - 1
	quicksortDualPivotOriginal(A, left, right)
}

fun quicksortDualPivotOriginal(A: Array<Int>, left: Int, right: Int) {
	if(right-left >= 1) {
		var p = A[left]
		var q = A[right]
		if (p > q) {
			swap(A, left, right)
			p = A[left]
			q = A[right]
		}
		var l = left + 1
		var g = right - 1
		var k = l
		while (k <= g) {
			if (A[k] < p) {
				swap(A,k,l)
				l = l + 1
			} else {
				if (A[k] > q) {
					while (A[g] > q && k < g) {
						g = g - 1
					}
					swap(A,k,g)
					g = g - 1
					if(A[k] < p) {
						swap(A,k,l)
						l = l + 1
					}
				}
			}
			k = k + 1
		}
		l = l - 1
		g = g + 1
		swap(A,left,l)
		swap(A,right,g)
		quicksortDualPivotOriginal(A,left,l-1)
		quicksortDualPivotOriginal(A,l+1,g-1)
		quicksortDualPivotOriginal(A,g+1,right)
	}
}

// Extra

fun swap (A: Array<Int>,x: Int, y: Int) {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
}

/*fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}

fun imprimiArreglo(A: Array<Int>) {
	for (i in 0 until A.size) {
		print("${A[i]} ")
	}
	println("")
}

fun main(args: Array<String>) {
	var n = args[0].toInt()
	var A = Array(n,{0})
	for(i in 0 until n) {
		A[i] = (0..n).random()
	}
	//imprimiArreglo(A)
	quicksortClasico(A)
	//imprimiArreglo(A)
	if (estaEnOrdenAscendente(A) == true) {
		println("Ordenado")
	} else {
		println("No ordenado")
	}
}*/
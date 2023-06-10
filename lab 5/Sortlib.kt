/**
 * Sortlib corresponde a una librería que implementa los siguientes algoritmos de ordenamiento:
 * Insertion Sort, Selection Sort, Bubblesort, Shellsort, Mergesort, Heapsort,
 * SmoothSort, Quicksort clásico, Quicksort Three Way y Quicksort Dual Pivot.
 * Se implementa un procedimiento swap para intercambiar dos elementos dentro un arreglo,
 * además de otros procedimientos necesarios para algunos algoritmos.
 * 
 * Desarrollado por: Castillo, Haydeé y Prieto, Jesús.
 */ 

import kotlin.math.max

fun swap (A: Array<Int>,x: Int, y: Int) {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
}

fun bubbleSort(A: Array<Int>) {
    for (i in 0 until (A.size-1)) {
        for (j in (A.size-1) downTo (i+1)) {
            if (A[j] < A[j-1]) {
                swap(A, j, j-1)
            }
        }
    } 
}

fun insertionSort(A: Array<Int>) {
    for (i in 1 until A.size) {
        var j: Int = i
        while (A[j] < A[j-1]) {
            swap(A, j, j-1)
            j = j-1
            if (j == 0)
                break
        }
    }
}

fun selectionSort(A: Array<Int>) {
    for (i in 0 until (A.size-1)) {
        var lowIndex: Int = i
        var lowKey: Int = A[i]
        for (j in (i+1) until A.size) {
            if (A[j] < lowKey) {
                lowKey = A[j]
                lowIndex = j
            }
        }
        swap(A, i, lowIndex)
    }
}

fun shellSort(A: Array<Int>) {
    var incr: Int = A.size/2
    while (incr > 0) {
        for (i in (incr+1) until A.size) {
            var j: Int = i-incr
            while (j > -1) {
                if (A[j] > A[j+incr]) {
                    swap(A, j, j+incr)
                    j = j-incr
                } else {
                    j = -1
                }
            }
        }
        incr = incr/2    
    }
}

fun mergesortInsertion(T: Array<Int>) {   
    if(T.size <= 90){
        insertionSort(T)
    } else {
        var k = T.size/2
        var m = T.size/2
        if (T.size%2 != 0){
            m = T.size/2 + 1
        }
        var U = Array<Int>((1+k), {0})
        var V = Array<Int>((1+m), {0})
        for (i in 0 until k) {
            U[i] = T[i]
        }
        // Centinela, garantiza que al ordenar U en la llamada recursiva los primeros T.size elementos serán del arreglo T
        U[k] = Int.MAX_VALUE-1 
        for (i in 0 until m) {
            V[i] = T[i+k]
        }
        // Centinela, garantiza que al ordenar V en la llamada recursiva los primeros T.size elementos serán del arreglo T
        V[m] = Int.MAX_VALUE-1
        mergesortInsertion(U)
        mergesortInsertion(V)
        merge(U,V,T)
    }
}

// Funciones usadas por Mergesort:
// Merge: Une dos arreglos ordenados en un arreglo ordenado

fun merge(U: Array<Int>, V: Array<Int>, T: Array<Int>) {
	var i = 0
	var j = 0
	U[(U.size-1)] = Int.MAX_VALUE // Centinela, garantiza que no haya errores de rango al evaluar los condicionales
	V[(V.size-1)] = Int.MAX_VALUE // Centinela, garantiza que no haya errores de rango al evaluar los condicionales
	for (k in 0 until (U.size+V.size-2)) {
		if (U[i] <= V[j]) {
			T[k] = U[i]
			i = i+1
		} else {
			T[k] = V[j]
			j = j+1
		}
	}
}


fun heapsort(A: Array<Int>) {
	buildMaxHeap(A)
	var n = A.size
	for (i in (n-1) downTo 1) {
		swap(A, 0, i)
		maxHeapify(A,0,i-1)
	}
}

// Funciones usadas por Heapsort:
// MaxHeapify : Verifica si se cumple la defición de MAX-HEAP
// BuildMaxHeap : Convierte el arreglo en un MAX-HEAP

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

fun smoothsort(m: Array<Int>) {
	var N = m.size
	var aux = Array(8, {0}) // Herramienta auxiliar
	aux[0] = 1 // Corresponde a q := 1
	aux[1] = 0 // Corresponde a r := 0
	aux[2] = 1 // Corresponde a p := 1
	aux[3] = 1 // Corresponde a b := 1
	aux[4] = 1 // Corresponde a c := 1
	aux[5] = 0 // Corresponde a r1, inicializando
	aux[6] = 0 // Corresponde a b1, inicializando
	aux[7] = 0 // Corresponde a c1, inicializando
	while (aux[0] != N) { // do q != N ->
		aux[5] = aux[1] // r1 := r
		if (aux[2]%8 == 3) { // if p mod 8 = 3 ->
			aux[6] = aux[3] // b1 := b
			aux[7] = aux[4] // c1 := c
			sift(m,aux) // sift
			aux[2] = (aux[2]+1)/4 // p := (p+1)/4
			up(aux) // up
			up(aux) // up
		} else if (aux[2]%4 == 1) { // [] p mod 4 = 1 ->
			if ((aux[0] + aux[4]) < N) { // if q + c < N ->
				aux[6] = aux[3] // b1 := b
				aux[7] = aux[4] // c1 := c
				sift(m, aux) // sift
			} else { // [] q + c >= N ->
				trinkle(m,aux) // trinkle
			}
			down(aux) // down
			aux[2] = 2*aux[2] // p := 2*p
			while (aux[3] != 1) { // do b != 1 ->
				down(aux) // down
				aux[2] = 2*aux[2] // p := 2*p
			}
			aux[2] = aux[2] + 1 // p := p + 1 
		}
		aux[0] = aux[0] + 1 // q := q + 1
		aux[1] = aux[1] + 1 // r := r+1
	}
	aux[5] = aux[1] // r1 := r
	trinkle(m,aux) // trinkle
	while (aux[0] != 1) { // do q != 1 -> 
		aux[0] = aux[0] - 1 // q := q-1
		if (aux[3] == 1) { // if b = 1 ->
			aux[1] = aux[1] - 1 // r := r-1
			aux[2] = aux[2] - 1 // p := p-1
			while (even(aux[2]) == true) { // do even(p) ->
				aux[2] = aux[2]/2 // p := p/2
				up(aux) // up
			}
		} else if (aux[3] >= 3) { // [] b >= 3
			aux[2] = aux[2] - 1 // p := p - 1
			aux[1] = aux[1] - aux[3] + aux[4] // r := r - b + c
			if (aux[2] > 0) { // [] p > 0 ->
				semitrinkle(m, aux) // semitrinkle
			}
			down(aux) // down
			aux[2] = 2*aux[2] + 1 // p := 2*p + 1
			aux[1] = aux[1] + aux[4] // r := r + c
			semitrinkle(m,aux) // semitrinkle
			down(aux) // down
			aux[2] = 2*aux[2] + 1 // p := 2*p + 1
		}
	}
}
// Funciones usadas por SmoothSort:
// Up/Up1 : Funciones que suman los valores b/b1 y c/c1
// Down/Down : Funciones restan los valores b/b1 y c/c1
// Even: Verifica si el valor ingresado es par
// Trinkle 
// SemiTrinkle
// Sitf

fun up(aux: Array<Int>) { // b,c := b+c+1, b
	var tmp = aux[3]
	aux[3] = aux[3] + aux[4] + 1
	aux[4] = tmp
}

fun down(aux: Array<Int>) { // b,c := c, b-c-1
	var tmp = aux[4]
	aux[4] = aux[3] - aux[4] - 1
	aux[3] = tmp
}

fun up1(aux: Array<Int>) { // b1, c1 = b1 + c1 + 1, b1
	var tmp = aux[6]
	aux[6] = aux[6] + aux[7] + 1
	aux[7] = tmp
}

fun down1(aux: Array<Int>) { // b1, c1 = c1, b1 - c1 - 1
	var tmp = aux[7]
	aux[7] = aux[6] - aux[7] - 1
	aux[6] = tmp
}

fun sift(m: Array<Int>, aux: Array<Int>) {
	while(aux[6] >= 3) { // do b1 >= 3 ->
		var r2 = aux[5] - aux[6] + aux[7] // r2 := r1 - b1 + c1
		if(m[r2] <= m[aux[5]-1]) { // [] m(r2) <= m(r1-1) 
			r2 = aux[5] - 1 // r2 := r1 -1
			down1(aux) // down1
		}
		if (m[aux[5]] >= m[r2]) { // if m(r1) >= m(r2) ->
			aux[6] = 1 // b1 := 1
		} else { // [] m(r1) < m(r2) ->
			swap(m, aux[5], r2) // m:swap(r1,r2)
			aux[5] = r2 // r1 := r2
			down1(aux) // down1
		}
	}
}

fun trinkle(m: Array<Int>, aux: Array<Int>) {
	var p1 = aux[2] // p1 := p
	aux[6] = aux[3] // b1 := b
	aux[7] = aux[4] // c1 := c
	while (p1 > 0) { // do p1 > 0 ->
		while (even(p1) == true) { // do even(p1) ->
			p1 = p1/2 // p1 := p1/2
			up1(aux) // up1
		}
		var r3 = aux[5] - aux[6] // r3 := r1 - b1 
		if (p1 == 1 || m[r3] <= m[aux[5]]) { // p1 = 1 cor m(r3) <= m(r1) ->
			p1 = 0 // p1 = 0
		} else if (p1 > 1 && m[r3] > m[aux[5]]) { // p1 cand m(r3) > m(r1)
			p1 = p1 - 1 // p1 := p1 - 1
			if (aux[6] == 1) { // if b1 = 1 ->
				swap(m, aux[5], r3) // m: swap(r1, r3)
				aux[5] = r3 // r1 := r3
			} else if (aux[6] >= 3) { // [] b1 >= 3 ->
				var r2 = aux[5] - aux[6] + aux[7] // r2 := r1 - b1 + c1
				if(m[r2] <= m[aux[5]-1]) { // [] m(r2) <= m(r1-1)
					r2 = aux[5] -1 // r2 := r1 - 1
					down1(aux) //down1
					p1 = 2*p1 //p1 = 2*p1
				}
				if (m[r3] >= m[r2]) { // if m(r3) >= m(r2)
					swap(m, aux[5], r3) // m:swap(r1,r3)
					aux[5] = r3 // r1 := r3
				} else { // [] m(r3) < m(r2)
					swap(m, aux[5], r2) // m:swap(r1,r2)
					aux[5] = r2 // r1 := r2
					down1(aux) // down1
					p1 = 0 // p1 := 0
				}
			}
		}
	}
	sift(m, aux) // sift
}

fun even(p1: Int): Boolean {
	if(p1%2 == 0) {
		return true
	} else {
		return false
	}
}

fun semitrinkle(m: Array<Int>, aux: Array<Int>) {
	aux[5] = aux[1] - aux[4] // r1 := r-c
	if (m[aux[5]] > m[aux[1]]) { // [] m(r1) > m(r) ->
		swap(m,aux[1],aux[5]) // m: swap(r,r1)
		trinkle(m, aux) // trinkle
	}
}

// Funciones usadas por Quicksort Classic:
// -particionClasico
// -quisorktClasicoOriginal

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

// Funciones usadas por Quicksort Three Way:
// -quicksortThreeWayOriginal

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

// Funciones usadas por Quicksort Dual Pivot:
// -quicksortDualPivotOriginal

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

fun countingSort(A: Array<Int>) {
	var k = A.max()
	var B = Array(A.size){0}
	var C = Array(k+1){0}
	
	for (j in 0 until A.size) {
		C[A[j]] = C[A[j]]+1
	}

	for (i in 1 until C.size) {
		C[i] = C[i] + C[i-1]
	}
		
	for (j in A.size-1 downTo 0) {
		B[C[A[j]]-1] = A[j] 
		C[A[j]] = C[A[j]] -1
	}
	for (i in 0 until A.size) {
		A[i] = B[i]
	}
}

fun radixSort(A: Array<Int>) {
	var d = digitos(A)
	for (i in 1..d) {
		var B = posicion(A, i)
		crSort(A, B)
	}
}

fun digitos(A: Array<Int>): Int {
	var k = A.max()
	var i = 10
	var j = 1
	while (k/i > 0) {
		i = i*10
		j++
	}
	return j
}

fun posicion(A: Array<Int>, d: Int): Array<Int> {
	var B = Array(A.size){0}
	var divisor = potencia(d)
	for (i in 0 until A.size) {
		B[i] = (A[i]/divisor)%10
	}
	return B
}

fun potencia(a: Int): Int {
	var i = 1
	var b = 1
	if (a == 1) {
		return 1
	} else {
		while (i < a) {
			b = b*10 
			i++
		}
		return b
	}
}
fun crSort(A: Array<Int>, D: Array<Int>) {
	var k = D.max()
	var B = Array(D.size){0}
	var C = Array(k+1){0}

	for (j in 0 until D.size) {
		C[D[j]] = C[D[j]]+1
	}
	for (i in 1 until C.size) {
		C[i] = C[i] + C[i-1]
	}
	for (j in D.size-1 downTo 0) {
		B[C[D[j]]-1] = A[j] 
		C[D[j]] = C[D[j]] -1
	}
	for (i in 0 until A.size) {
		A[i] = B[i]
	}
}
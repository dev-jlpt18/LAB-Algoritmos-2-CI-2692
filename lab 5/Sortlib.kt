/**
 * Sortlib corresponde a una librería que implementa los siguientes algoritmos de ordenamiento:
 * Insertion Sort, Selection Sort, Bubblesort, Shellsort, Mergesort, Heapsort,
 * SmoothSort, Quicksort clásico, Quicksort Three Way, Quicksort Dual Pivot, Counting Sort y Radix Sort.
 * Además, se implementa un procedimiento swap para intercambiar dos elementos dentro un arreglo,
 * en conjunto con otras funciones y procedimientos necesarios para algunos algoritmos.
 * 
 * Desarrollado por: Castillo, Haydeé y Prieto, Jesús.
 */ 

// Librerias importadas
import kotlin.math.max
import kotlin.math.roundToInt

// Procedimiento Swap

fun swap (A: Array<Int>,x: Int, y: Int) {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
}

// Algoritmo: Bubblesort

fun bubbleSort(A: Array<Int>) {
    for (i in 0 until (A.size-1)) {
        for (j in (A.size-1) downTo (i+1)) {
            if (A[j] < A[j-1]) {
                swap(A, j, j-1)
            }
        }
    } 
}

// Algoritmo: Insertion Sort

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

// Algoritmo: Selection Sort

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

// Algoritmo: Shellsort

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

/* Algoritmo: Mergesort-Insertion
 * Funciones y procedimientos asociados:
 * Merge: Este procedimiento une dos arreglos ordenados en un arreglo ordenado
 */

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

/* Algoritmo: Heapsort
 * Funciones y procedimientos asociados:
 * MaxHeapify: Este procedimiento se encarga de mantener la propidad de max-heap
 * BuildMaxHeap: Este procedimiento convierte un arreglo dado en un max-heap
 */

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

/* Algoritmo: Smoothsort
 * Funciones y procedimientos asociados:
 * Even: Verifica si el valor ingresado es par
 * Up
 * Up1
 * Down
 * Down1
 * Sitf 
 * Trinkle 
 * SemiTrinkle
 */

fun smoothsort(m: Array<Int>) {
	var N = m.size
	var aux = Array(8, {0}) // Herramienta auxiliar
	aux[0] = 1 // Corresponde a q
	aux[1] = 0 // Corresponde a r
	aux[2] = 1 // Corresponde a p
	aux[3] = 1 // Corresponde a b
	aux[4] = 1 // Corresponde a c
	aux[5] = 0 // Corresponde a r1
	aux[6] = 0 // Corresponde a b1
	aux[7] = 0 // Corresponde a c1
	while (aux[0] != N) {
		aux[5] = aux[1]
		if (aux[2]%8 == 3) {
			aux[6] = aux[3]
			aux[7] = aux[4]
			sift(m,aux)
			aux[2] = (aux[2]+1)/4
			up(aux)
			up(aux)
		} else if (aux[2]%4 == 1) {
			if ((aux[0] + aux[4]) < N) {
				aux[6] = aux[3]
				aux[7] = aux[4]
				sift(m, aux)
			} else {
				trinkle(m,aux)
			}
			down(aux)
			aux[2] = 2*aux[2]
			while (aux[3] != 1) {
				down(aux)
				aux[2] = 2*aux[2]
			}
			aux[2] = aux[2] + 1
		}
		aux[0] = aux[0] + 1
		aux[1] = aux[1] + 1
	}
	aux[5] = aux[1]
	trinkle(m,aux)
	while (aux[0] != 1) {
		aux[0] = aux[0] - 1
		if (aux[3] == 1) {
			aux[1] = aux[1] - 1
			aux[2] = aux[2] - 1
			while (even(aux[2]) == true) {
				aux[2] = aux[2]/2
				up(aux)
			}
		} else if (aux[3] >= 3) {
			aux[2] = aux[2] - 1
			aux[1] = aux[1] - aux[3] + aux[4]
			if (aux[2] > 0) {
				semitrinkle(m, aux)
			}
			down(aux)
			aux[2] = 2*aux[2] + 1
			aux[1] = aux[1] + aux[4]
			semitrinkle(m,aux)
			down(aux)
			aux[2] = 2*aux[2] + 1
		}
	}
}

fun even(p1: Int): Boolean {
	if(p1%2 == 0) {
		return true
	} else {
		return false
	}
}

fun up(aux: Array<Int>) {
	var tmp = aux[3]
	aux[3] = aux[3] + aux[4] + 1
	aux[4] = tmp
}

fun up1(aux: Array<Int>) {
	var tmp = aux[6]
	aux[6] = aux[6] + aux[7] + 1
	aux[7] = tmp
}

fun down(aux: Array<Int>) {
	var tmp = aux[4]
	aux[4] = aux[3] - aux[4] - 1
	aux[3] = tmp
}

fun down1(aux: Array<Int>) {
	var tmp = aux[7]
	aux[7] = aux[6] - aux[7] - 1
	aux[6] = tmp
}

fun sift(m: Array<Int>, aux: Array<Int>) {
	while(aux[6] >= 3) {
		var r2 = aux[5] - aux[6] + aux[7]
		if(m[r2] <= m[aux[5]-1]) {
			r2 = aux[5] - 1
			down1(aux)
		}
		if (m[aux[5]] >= m[r2]) {
			aux[6] = 1
		} else {
			swap(m, aux[5], r2)
			aux[5] = r2
			down1(aux)
		}
	}
}

fun trinkle(m: Array<Int>, aux: Array<Int>) {
	var p1 = aux[2]
	aux[6] = aux[3]
	aux[7] = aux[4] 
	while (p1 > 0) {
		while (even(p1) == true) {
			p1 = p1/2
			up1(aux)
		}
		var r3 = aux[5] - aux[6]
		if (p1 == 1 || m[r3] <= m[aux[5]]) {
			p1 = 0
		} else if (p1 > 1 && m[r3] > m[aux[5]]) {
			p1 = p1 - 1
			if (aux[6] == 1) {
				swap(m, aux[5], r3)
				aux[5] = r3
			} else if (aux[6] >= 3) {
				var r2 = aux[5] - aux[6] + aux[7]
				if(m[r2] <= m[aux[5]-1]) {
					r2 = aux[5] -1
					down1(aux)
					p1 = 2*p1
				}
				if (m[r3] >= m[r2]) {
					swap(m, aux[5], r3)
					aux[5] = r3
				} else {
					swap(m, aux[5], r2)
					aux[5] = r2
					down1(aux)
					p1 = 0
				}
			}
		}
	}
	sift(m, aux)
}

fun semitrinkle(m: Array<Int>, aux: Array<Int>) {
	aux[5] = aux[1] - aux[4] 
	if (m[aux[5]] > m[aux[1]]) {
		swap(m,aux[1],aux[5])
		trinkle(m, aux)
	}
}

/* Algoritmo: Quicksort Clásico
 * Funciones y procedimientos asociados:
 * ParticionClasico: Esta función genera una partición de un arreglo dado, de acuerdo al esquema tradicional de partición de
 * Quicksort
 * QuicksortClasicoOriginal: Implementación de Quicksort que recibe un arreglo y dos enteros que determinan el subarreglo a ordenar
 */

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

/* Algoritmo: Quicksort Three Way
 * Funciones y procedimientos asociados:
 * QuicksortThreeWayOriginal: Implementación de Quicksort Three Way que recibe un arreglo y dos enteros que determinan 
 * el subarreglo a ordenar
 */

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

/* Algoritmo: Quicksort Dual Pivot
 * Funciones y procedimientos asociados:
 * QuicksortThreeWayOriginal: Implementación de Quicksort Dual Pivot que recibe un arreglo y dos enteros que determinan 
 * el subarreglo a ordenar
 */

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

// Algoritmo: Counting Sort

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

/* Algoritmo: Radix sort
 * Funciones y procedimientos asociados:
 * digitos: Esta función obtiene el número de dígitos del elemento máximo de un arreglo dado
 * posicion: Esta función genera un arreglo con los digitos en la posición d, de los elementos de un arreglo dado
 * potencia: Esta función recibe un a: Int y retorna una potencia de base 10 y exponente a-1
 * crSort: Este procedimiento es una versión modificada de Counting Sort que permite ordenar un arreglo A de acuerdo a
 * los dígitos de un arreglo D dado
 */

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

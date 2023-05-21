/**
 * Sortlib corresponde a una librería que implementa los siguientes algoritmos de ordenamiento:
 * Insertion Sort, Selection Sort, Bubblesort, Shellsort y Mergesort.
 * Además se implementa un procedimiento swap para intercambiar dos elementos dentro un arreglo.
 * 
 * Desarrollado por: Castillo, Haydeé y Prieto, Jesús.
 */ 

fun swap (A: Array<Int>,x: Int, y: Int): Unit {
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
        mergesortInsertionPrueba(U,n)
        mergesortInsertionPrueba(V,n)
        merge(U,V,T)
    }
}
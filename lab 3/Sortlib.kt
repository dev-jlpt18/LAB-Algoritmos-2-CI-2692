import kotlin.floatArrayOf

/**
 * Sortlib corresponde a una librería que implementa los siguientes algoritmos de ordenamiento simples:
 * Insertion Sort, Selection Sort, Bubblesort y Shellsort.
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

fun heapsort(A: Array<Int>) {
    buildMaxHeap(A, A.size)
    for (i in A.size-1 downTo 1) {
        swap(A, 0, i)
        maxHeapify(A, 0)
    }
}

fun maxHeapify (A: Array<Int>, i: Int) {
    var left = 2*i+1
    var right = (i+1)*2
    var largest: Int
    if (left <= A.size && A[left] > A[i]) {
        largest = left
    } else {
        largest = i
    }
    if (right <= A.size && A[right] > A[largest]) {
        largest = right
    }
    if (largest != i) {
        swap(A, i, largest)
        maxHeapify(A, largest)
    }
}

fun buildMaxHeap (A: Array<Int>, n: Int) {
    for (i in (n/2-1) downTo 0) {
        maxHeapify(A, i)
    }
}

fun smoothSort (A: Array<Int>) {
    var (q ,r) = Pair(1, 0)
    var (p, b, c) = Triple(1, 1, 1)
    var (r1, b1, c1) = Triple(0, 0, 0)
    while (q != A.size) {
        r1 = r
        if (p%8 == 3) {
            b1 = b
            c1 = c
            sift(A, r1, b1, c1)
            p = (p+1)/4
            // primer up
            b = b +c+1
            c = b
            // segundo up
            b = b +c+1
            c = b
        } else if (p%1 == 1) {
            if (q+c < A.size) {
                b1 = b
                c1 = c
                (r1, b1, c1) = sift(A, r1, b1, c1)
            } else {
                trinkle()
            }
            while (b != 1) {
                // down
                b = c
                c = b-c-1

                p = 2*p
                p = p +1
            }
        }
        q = q+1
        r = r+1
    }
    r1 = r
    trinkle()
    while (q != 1) {
        q = q-1
        if (b = 1) {
            r = r-1
            p = p-1
            while (even(p)) {
                p = p/2
                // primer up
                b = b +c+1
                c = b
            }
        } else if (b >= 3) {
            p = p-1
            r1 = r-b+c
            if (p > 0) {
                semitrinkle()
            }
            // down
            b = c
            c = b-c-1

            p = 2*p +1
            r = r+c

            semitrinkle()

            // down
            b = c
            c = b-c-1

            p = 2*p +1
        }
    }
}

fun sitf(A: Array<Int>, r1 : Int, b1: Int, c1: Int): Triple<Int, Int, Int> {
    var r2 = r1-b1+c1
    var b2 = b1
    var c2 = c1
    var r3 = r1
    while (b2 >= 3) {
        if (A[r2] <= A[r1-1]) {
            r2 = r1 -1
            //down1
            b2 = c2
            c2 = b2-c2-1
        }
        if (A[r1] >= A[r2]) {
            b2 = 1
        } else {
            swap(A, r1, r2)
            r3 = r2
            //down1
            b2 = c2
            c2 = b2-c2-1
        }
    }
    return Triple(r3, b2, c2)
}
fun swap (A: Array<Int>,x: Int, y: Int): Unit {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
}

fun bubbleSort(A: Array<Int>) {
    // Realizar!
    for (i in 0 until (A.size - 2)) {
        for (j in (A.size - 1) downTo (i+1)) {
            if (A[j] < A[j-1]) {
                swap(A, j, j-1)
            }
        }
    } 
}

fun insertionSort(A: Array<Int>) {
    // Realizar!
    for (i in 1 until A.size) {
        var j: Int = i
        while (A[j] < A[j-1]) {
            if (j == 0)
                break
            swap(A, j, j-1)
            j = j-1 
        }
    }
}

fun selectionSort(A: Array<Int>) {
    // Realizar!
    for (i in 0 until (A.size-2)) {
        var lowIndex: Int = i
        var lowKey: Int = A[i]
        for (j in (i+1) until (A.size-1)) {
            if (A[j] < lowKey) {
                lowKey = A[j]
                lowIndex = j
            }
        }
        swap(A, i, lowIndex)
    }
}

fun shellSort(A: Array<Int>) {
    // Realizar!
    var i: Int = 0
    var j: Int = 0
    var incr: Int = A.size/2
    while (incr > 0) {
        for (k in (incr+1) until (A.size-1)) {
            j = k-incr
            while (j > 0) {
                if (A[j] > A[j+incr]) {
                    swap(A, j, j+incr)
                    j = j-incr
                } else {
                    j = 0
                }
            }
        }
        incr = incr/2    
    }
}

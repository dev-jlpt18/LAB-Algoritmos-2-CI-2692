fun swap (x: Int, y: Int) {
    var temp: Int = x
    x = y
    y = temp
}

fun bubbleSort(A: Array<Int>) {
    // Realizar!
    for (i in 0 until (A.size - 2)) {
        for (j in (A.size - 1) downTo (i+1)) {
            if (A[j] < A[j-1]) {
                swap(A[j], A[j-1])
            }
        }
    } 
}

fun insertionSort(A: Array<Int>) {
    // Realizar!
    for (i in 1 until (A.size-1)) {
        var j: Int = i;
        while (A[j] < A[j-1]) {
            swap(A[j], A[j-1])
            j-= 1
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
        swap(A[i], A[lowIndex])
    }
}

fun shellSort(A: Array<Int>) {
    // Realizar!
    var i: Int = 0
    var j: Int = 0
    var incr: Int = n div 2
    while (incr > 0) {
        for (i in (incr+1) until (A.size-1)) {
            j = i-incr
            while (j > 0) {
                if (A[j] > A[j+incr]) {
                    swap(A[j], A[j+incr])
                    j = j-incr
                } else {
                    j = 0
                }
            }
        }
        incr = incr div 2    
    }
}

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

fun heapSort(A: Array<Int>) {
    buildMaxHeap(A, A.size)
    for (i in A.size-1 downTo 1) {
        swap(A, 0, i)
        maxHeapify(A, 0, i-1)
    }
}

fun maxHeapify(A: Array<Int>, i: Int, heap: Int) {
    var left = 2*i+1
    var right = (i+1)*2
    var largest: Int
    if (left <= heap && A[left] > A[i]) {
        largest = left
    } else {
        largest = i
    }
    if (right <= heap && A[right] > A[largest]) {
        largest = right
    }
    if (largest != i) {
        swap(A, i, largest)
        maxHeapify(A, largest, heap)
    }
}

fun buildMaxHeap(A: Array<Int>, n: Int) {
    for (i in (n/2-1) downTo 0) {
        maxHeapify(A, i, n-1)
    }
}


fun smoothSort(A: Array<Int>) {
    var q = 1
    var r = 0
    var p = 1
    var b = 1
    var c = 1
    var r1 = 0
    var b1 = 0
    var c1 = 0
    var tripleta: Triple<Int, Int, Int>
    var pares : Pair<Int, Int>
    while (q != A.size) {
        r1 = r
        if (p%8 == 3) {
            b1 = b
            c1 = c
            tripleta = sitf(A, r1, b1, c1)
            r1 = tripleta.first
            b1 = tripleta.second
            c1 = tripleta.third

            p = (p+1)/4
            // primer up
            pares = up(b, c)
            b = pares.first
            c = pares.second
            // segundo up
            pares = up(b, c)
            b = pares.first
            c = pares.second
        } else if (p%4 == 1) {
            if (q+c < A.size) {
                b1 = b
                c1 = c
                tripleta = sitf(A, r1, b1, c1)
                r1 = tripleta.first
                b1 = tripleta.second
                c1 = tripleta.third
            } else {
                tripleta = trinkle(A, r1, p, b, c)
                r1 = tripleta.first
                b1 = tripleta.second
                c1 = tripleta.third
            }
            // down
            pares = down(b, c)
            b = pares.first
            c = pares.second

            p = 2*p
            while (b != 1) {
                // down
                pares = down(b, c)
                b = pares.first
                c = pares.second

                p = 2*p   
            }
            p = p +1
        }
        q = q+1
        r = r+1
    }
    r1 = r
    tripleta = trinkle(A, r1, p, b, c)
    r1 = tripleta.first
    b1 = tripleta.second
    c1 = tripleta.third
    while (q != 1) {
        q = q-1
        if (b == 1) {
            r = r-1
            p = p-1
            while (even(p)) {
                p = p/2
                // primer up
                pares = up(b, c)
                b = pares.first
                c = pares.second
            }
        } else if (b >= 3) {
            p = p-1
            r = r-b+c
            if (p > 0) {
                tripleta = semitrinkle(A, b1, c1, p, b, r, c)
                r1 = tripleta.first
                b1 = tripleta.second
                c1 = tripleta.third
            }
            // down
            pares = down(b, c)
            b = pares.first
            c = pares.second

            p = 2*p +1
            r = r+c

            tripleta = semitrinkle(A, b1, c1, p, b, r, c)
            r1 = tripleta.first
            b1 = tripleta.second
            c1 = tripleta.third

            // down
            pares = down(b, c)
            b = pares.first
            c = pares.second

            p = 2*p +1
        }
    }
}
fun up(x:Int, y: Int): Pair<Int, Int> {
    var b = x+y+1
    var c = x
    return Pair(b, c)
}

fun down(x:Int, y: Int): Pair<Int, Int> {
    var b = y
    var c = x-y-1
    return Pair(b, c)
}
fun sitf(A: Array<Int>, r1: Int, b1: Int, c1: Int): Triple<Int, Int, Int> {
    var b2 = b1
    var c2 = c1
    var r2 = r1
    var pares : Pair<Int, Int>
    while (b2 >= 3) {
        var r3 = r2-b2+c2
        if (A[r3] <= A[r2-1]) {
            r3 = r2 -1
            //down1
            pares = down(b2,c2)
            b2 = pares.first
            c2 = pares.second
        }
        if (A[r2] >= A[r3]) {
            b2 = 1
        } else {
            swap(A, r2, r3)
            r2 = r3
            //down1
            pares = down(b2,c2)
            b2 = pares.first
            c2 = pares.second
        }
    }
    return Triple(r2,b2,c2)
}

fun semitrinkle(A: Array<Int>, b1: Int, c1: Int, p: Int, b: Int, r: Int, c: Int): Triple<Int, Int, Int>  {
    var r2 = r -c
    if (A[r2] > A[r]) {
        swap(A, r, r2)
        return trinkle(A, r2, p, b, c)
    }
    return Triple(r2, b1, c1)
}

fun trinkle(A: Array<Int>, r1: Int, p: Int, b: Int, c: Int): Triple<Int, Int, Int> {
    var p1 = p
    var b1 = b
    var c1 = c
    var r2 = r1
    var pares: Pair<Int, Int>
    while (p1 > 0) {
        var r3: Int
        while (even(p1)) {
            p1 = p1/2
            // up1
            pares = up(b1,c1)
            b1 = pares.first
            c1 = pares.second
        }
        r3 = r2 - b1
        if (p1 == 1 || A[r3] <= A[r2]) {
            p1 = 0
        } else if (p1 > 1 && A[r3] > A[r2]) {
            p1 = p1 -1
            if (b1 == 1) {
                swap(A, r2, r3)
                r2 = r3
            } else if (b1 >= 3) {
                var r4 = r2-b1+c1
                if (A[r4] <= A[r2-1]) {
                    r4 = r2-1
                    // down1
                    pares = down(b1,c1)
                    b1 = pares.first
                    c1 = pares.second

                    p1 = 2*p1
                }
                if (A[r3] >= A[r4]) {
                    swap(A, r2, r3)
                    r2 = r3
                } else {
                    swap(A, r2, r4)
                    r2 = r4
                    // down1
                    pares = down(b1,c1)
                    b1 = pares.first
                    c1 = pares.second

                    p1 = 0
                }
            }
        }
    }
    var tripleta = sitf(A, r2, b1, c1)
    r2 = tripleta.first
    b1 = tripleta.second
    c1 = tripleta.third
    return Triple(r2,b1,c1)
}

fun even(x: Int): Boolean {
    if (x%2 == 0) {
        return true
    }
    return false 
}

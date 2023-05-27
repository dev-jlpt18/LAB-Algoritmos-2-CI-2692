fun main() {
    val A = arrayOf(1,6,9,8,7,10,5,4)
    smoothSort(A)
    println(A.contentToString())
    // Colocamos el Pair dentro de la variable 'miPair' 
    var miPair = Pair("Carlos", 34)
    println(miPair.first)
    println(miPair.second) 
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

fun swap (A: Array<Int>,x: Int, y: Int): Unit {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
}

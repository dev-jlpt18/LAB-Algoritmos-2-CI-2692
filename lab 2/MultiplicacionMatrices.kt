fun main(n: Int) {
    var a = Array<Array<Int>>(10,{Array(10) {0}})
    var b = Array(5) {Array(5){0}}
    println(a.contentToString())
    println(b[0].contentToString())
    val A = generadorMatriz(n)
    val B = Array(n/2){Array(n/2){Array(n/2){0}}}
}

fun generadorMartriz(n: Int): Array<Array<Int>> {
    var A = Array(n){Array(n){0}}
    for (i in 0 until A.size) {
        for (j in 0..A[i].size) {
            A[i][j] =(0..9).random()
        }
    }
    return A
}

fun multiplicacionSimpleDeMatrices(A: Array<Array<Int>>, B: Array<Array<Int>>): Array<Array<Int>> {
    if (A[0].size != B.size) {
        var C = Array(1){Array(1){0}}
        return C
    } else {
        var C = Array(A.size){Array(B[0].size){0}}
        for (i in 0 until A.size){
            for (j in 0 until B.size) {
                for (k in 0 until A.size) {
                    C[i][j] = C[i][j] + A[i][k]*B[k][j]
                }
            }
        }
        return C 
    }
}

fun multiplicacionStrassen(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>>{
    var C = Array(N/2){Array(N/2){Array(N/2){0}}}
    var a = Array(N/2){Array(N/2){Array(N/2){0}}}
    var b = Array(N/2){Array(N/2){Array(N/2){0}}}

    return A;
}

fun particion(A: Array<Array<Int>>, D: Array<Array<Int>>, p: Int, q: Int, x: Int, y: Int ){
    for (i in p until x) {
        for (j in q until y) {
            D[i]
        }
    }
} 

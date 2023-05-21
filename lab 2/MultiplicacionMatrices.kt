// programa para intentos donde n es pertenece a potencia de 2
fun main(args: Array<String>) {
    var n = args[0].toInt()
    var a: Array<Array<Int>> = generadorMatriz(n)
    var b: Array<Array<Int>> = generadorMatriz(n)
    var tiempo1 = medirTiempoSimple(a, b, n)
    var tiempo2 = medirTiempoStrassen(a, b, n)
    println("Este es el primer tiempo : ${tiempo1}")
    println("Este es el segundo tiempo : ${tiempo2}")
}

fun generadorMatriz(n: Int): Array<Array<Int>> {
    var A = Array(n){Array(n){0}}
    for (i in 0 until A.size) {
        for (j in 0 until A.size) {
            A[i][j] =(0..9).random()
        }
    }
    return A
}

fun multiplicacionSimpleDeMatrices(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>> {
    if (A[0].size != B.size) {
        println("Error en la matriz")
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

fun multiplicacionStrassen(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>> {
    if (N == 1) {
		var C = Array(1){Array(1,{0})}
		C[0][0] = A[0][0]*B[0][0]
		return C
    } else {
        var C = Array(N){Array(N){0}}

        var A11 = Array(N/2){Array(N/2){0}}
        var A12 = Array(N/2){Array(N/2){0}}
        var A21 = Array(N/2){Array(N/2){0}}
        var A22 = Array(N/2){Array(N/2){0}}
        var B11 = Array(N/2){Array(N/2){0}}
        var B12 = Array(N/2){Array(N/2){0}}
        var B21 = Array(N/2){Array(N/2){0}}
        var B22 = Array(N/2){Array(N/2){0}}

        particion(A, A11,0, 0, N/2, N/2)
        particion(A, A12,0, N/2, N/2, N)
        particion(A, A21,N/2, 0, N, N/2)
        particion(A, A22,N/2, N/2, N, N)
        particion(B, B11,0, 0, N/2, N/2)
        particion(B, B12,0, N/2, N/2, N)
        particion(B, B21,N/2, 0, N, N/2)
        particion(B, B22,N/2, N/2, N, N)

        var S1 = restaMatriz(B12, B22)
        var S2 = sumaMatriz(A11, A12)
        var S3 = sumaMatriz(A21, A22)
        var S4 = restaMatriz(B21, B11)
        var S5 = sumaMatriz(A11, A22)
        var S6 = sumaMatriz(B11, B22)
        var S7 = restaMatriz(A12, A22)
        var S8 = sumaMatriz(B21, B22)
        var S9 = restaMatriz(A11, A21)
        var S10 = sumaMatriz(B11, B12)

        var P1 = multiplicacionStrassen(A11, S1, N/2)
        var P2 = multiplicacionStrassen(S2, B22, N/2)
        var P3 = multiplicacionStrassen(S3, B11, N/2)
        var P4 = multiplicacionStrassen(A22, S4, N/2)
        var P5 = multiplicacionStrassen(S5, S6, N/2)
        var P6 = multiplicacionStrassen(S7, S8, N/2)
        var P7 = multiplicacionStrassen(S9, S10, N/2)

        var C11 = restaMatriz(sumaMatriz(sumaMatriz(P5, P4), P6), P2)
        var C12 = sumaMatriz(P1, P2)
        var C21 = sumaMatriz(P3, P4)
        var C22 = restaMatriz(restaMatriz(sumaMatriz(P5, P1), P3), P7)

        union(C, C11, 0, N/2, 0, N/2)
        union(C, C12, 0, N/2, N/2, N)
        union(C, C21, N/2, N, 0,N/2)
        union(C, C22, N/2, N, N/2, N)

        return C
    }
}

fun particion(A: Array<Array<Int>>, B: Array<Array<Int>>, p: Int, q: Int, x: Int, y: Int) {
    var m = 0
    var n = 0
    for (i in p until x) {
        for (j in q until y) {
            B[m][n] = A[i][j]
            n = n + 1 
        }
        m = m +1
        n = 0
    }
}

fun union (A: Array<Array<Int>>, B: Array<Array<Int>>, p: Int, x: Int, q:Int, y:Int) {
    var m = 0
    var n = 0
    for (i in p until x) {
        for (j in q until y) {
            A[i][j] = B[m][n]
            n = n + 1 
        }
        m = m +1
        n = 0
    }
}
fun sumaMatriz(A: Array<Array<Int>>, B: Array<Array<Int>>): Array<Array<Int>>{
    var C = Array(A.size){Array(B.size){0}}
    for (i in 0 until A.size) {
        for (j in 0 until B.size) {
            C[i][j] = A[i][j]+B[i][j]
        }
    }
    return C
}

fun restaMatriz(A: Array<Array<Int>>, B: Array<Array<Int>>): Array<Array<Int>>{
    var C = Array(A.size){Array(B.size){0}}
    for (i in 0 until A.size) {
        for (j in 0 until B.size) {
            C[i][j] = A[i][j]-B[i][j]
        }
    }
    return C
}

fun medirTiempoSimple(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	multiplicacionSimpleDeMatrices(A,B,N)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun medirTiempoStrassen(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	multiplicacionStrassen(A,B,N)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

/*      Prueba de escritura
        var P1 = multiplicacionStrassen(sumaMatriz(A11,A22), sumaMatriz(B11, B22), N/2)
        var P2 = multiplicacionStrassen(sumaMatriz(A21,A22),B11, N/2)
        var P3 = multiplicacionStrassen(A11, restaMatriz(B12, B22), N/2)
        var P4 = multiplicacionStrassen(A22, restaMatriz(B21, B11), N/2)
        var P5 = multiplicacionStrassen(sumaMatriz(A11, A12), B22, N/2)
        var P6 = multiplicacionStrassen(restaMatriz(A21, A11), sumaMatriz(B11, B12), N/2)
        var P7 = multiplicacionStrassen(restaMatriz(A12, A22), sumaMatriz(B21, B22), N/2)

        var C11 = sumaMatriz(restaMatriz(sumaMatriz(P1, P4), P5), P7)
        var C12 = sumaMatriz(P3, P5)
        var C21 =  sumaMatriz(P2, P4)
        var C22 =  sumaMatriz(restaMatriz(sumaMatriz(P1, P3), P2), P6)

        union(C, C11, 0, N/2, 0, N/2)
        union(C, C12, 0, N/2, N/2, N)
        union(C, C21, N/2, N, 0,N/2)
        union(C, C22, N/2, N, N/2, N)

        return C 
*/
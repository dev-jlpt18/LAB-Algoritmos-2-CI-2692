fun multiplicacionSimpleDeMatrices(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>> {
	var C = Array(N){Array(N, {0})}
	for (i in 0 until N) {
		for (j in 0 until N) {
			for (k in 0 until N) {
				C[i][j] = C[i][j] + A[i][k]*B[k][j]
			}
		}
	}
	return C
}

fun multiplicacionStrassen(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>> {
	var C = Array(N){Array(N,{0})}
	if (N == 1) {
		C[0][0] = A[0][0]*B[0][0]
	} else {
		var logaritmoBase2DeN = Math.log(N.toDouble())/Math.log(2.0)
		if(logaritmoBase2DeN%1.0 != 0) {

		}
		var A11 = obtenerSubmatriz(A, 0, N/2, 0, N/2)
		var A12 = obtenerSubmatriz(A, 0, N/2, N/2, N)
		var A21 = obtenerSubmatriz(A, N/2, N, 0, N/2)
		var A22 = obtenerSubmatriz(A, N/2, N, N/2, N)
		var B11 = obtenerSubmatriz(B, 0, N/2, 0, N/2)
		var B12 = obtenerSubmatriz(B, 0, N/2, N/2, N)
		var B21 = obtenerSubmatriz(B, N/2, N, 0, N/2)
		var B22 = obtenerSubmatriz(B, N/2, N, N/2, N)
		var S1 = sumaMatricesCuadradas(B12, B22, N/2, false)
		var S2 = sumaMatricesCuadradas(A11, A12, N/2, true)
		var S3 = sumaMatricesCuadradas(A21, A22, N/2, true)
		var S4 = sumaMatricesCuadradas(B21, B11, N/2, false)
		var S5 = sumaMatricesCuadradas(A11, A22, N/2, true)
		var S6 = sumaMatricesCuadradas(B11, B22, N/2, true)
		var S7 = sumaMatricesCuadradas(A12, A22, N/2, false)
		var S8 = sumaMatricesCuadradas(B21, B22, N/2, true)
		var S9 = sumaMatricesCuadradas(A11, A21, N/2, false)
		var S10 = sumaMatricesCuadradas(B11, B12, N/2, true)
		var P1 = multiplicacionStrassen(A11, S1, N/2)
		var P2 = multiplicacionStrassen(S2, B22, N/2)
		var P3 = multiplicacionStrassen(S3, B11, N/2)
		var P4 = multiplicacionStrassen(A22, S4, N/2)
		var P5 = multiplicacionStrassen(S5, S6, N/2)
		var P6 = multiplicacionStrassen(S7, S8, N/2)
		var P7 = multiplicacionStrassen(S9, S10, N/2)
		var TMP = sumaMatricesCuadradas(P5, P4, N/2, true)
		var TMP2 = sumaMatricesCuadradas(P6, P2, N/2, false)
		var C11 = sumaMatricesCuadradas(TMP, TMP2, N/2, true)
		var C12 = sumaMatricesCuadradas(P1, P2, N/2, true)
		var C21 = sumaMatricesCuadradas(P3, P4, N/2, true)
		TMP = sumaMatricesCuadradas(P5, P1, N/2, true)
		TMP2 = sumaMatricesCuadradas(P3, P7, N/2, true)
		var C22 = sumaMatricesCuadradas(TMP, TMP2, N/2, false)
		asignarSubmatriz(C, C11, 0, 0)
		asignarSubmatriz(C, C12, 0, N/2)
		asignarSubmatriz(C, C21, N/2, 0)
		asignarSubmatriz(C, C22, N/2, N/2)
	}
	return C
}


fun sumaMatricesCuadradas(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int, S: Boolean): Array<Array<Int>> {
	var D = Array(N){Array(N,{0})}
	for (i in 0 until N) {
		for (j in 0 until N) {
			if(S == true){
				D[i][j] = A[i][j] + B[i][j]
			} else {
				D[i][j] = A[i][j] - B[i][j]
			}
		}
	}
	return D
}

/*
 * Función que extiende una matriz de dimensión N a una matriz de dimensión n, 
 * con n la potencia de 2 más cercana y mayor o igual a N
 */
fun ajustarDimensionesMatriz(A: Array<Array<Int>>, N: Int): Array<Array<Int>> {
	var tmp = Math.log(N.toDouble())/Math.log(2.0) // Se obtiene el logaritmo base 2 de N
	var n = tmp.toInt() + 1 // Se obtiene la potencia de 2 más cercana y mayor o igual a N
	var D = Array(n){Array(n, {0})}
	for (i in 0 until N) {
		for (j in 0 until N) {
			D[i][j] = A[i][j]
		}
	}
	return D 
}

// Función que retorna una submatriz desde A[filaInicio][columnaInicio] hasta A[filaFinal][columnaFinal], de una matriz A dada
fun obtenerSubmatriz(A: Array<Array<Int>>, filaInicio: Int, columnaInicio: Int, filaFinal: Int, columnaFinal: Int): Array<Array<Int>> {
	var filas = filaFinal - filaInicio + 1
	var columnas = columnaFinal - columnaInicio + 1
	var D = Array(filas){Array(columnas,{0})}
	for (i in 0 until filas) {
		for (j in 0 until columnas) {
			D[i][j] = A[filaInicio+i][columnaInicio+j]
		}
	}
	return D
}

fun asignarSubmatriz(C: Array<Array<Int>>, D: Array<Array<Int>>, filaInicio: Int, columnaInicio: Int) {
	for (i in 0 until D.size) {
		for (j in 0 until D[0].size) {
			C[i+filaInicio][j+columnaInicio] = D[i][j] 
		}
	}
}
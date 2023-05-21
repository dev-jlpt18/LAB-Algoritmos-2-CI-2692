// Función que realiza la multiplicación de matrices con el algoritmo estándar
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

// Función que realiza la multiplicación de matrices con el algoritmo de Strassen
fun multiplicacionStrassen(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>> {
	if (N == 1) {
		var C = Array(1){Array(1,{0})}
		C[0][0] = A[0][0]*B[0][0]
		return C
	} else {
		var b = esPotenciaDeDos(N)
		if (b == true) {
			var n = N
			var C = Array(n){Array(n,{0})}
			var A11 = obtenerSubmatriz(A, 0, 0, n/2, n/2)
			var A12 = obtenerSubmatriz(A, 0, n/2, n/2, N)
			var A21 = obtenerSubmatriz(A, n/2, 0, n, n/2)
			var A22 = obtenerSubmatriz(A, n/2, n/2, n, n)
			var B11 = obtenerSubmatriz(B, 0, 0, n/2, n/2)
			var B12 = obtenerSubmatriz(B, 0, n/2, n/2, n)
			var B21 = obtenerSubmatriz(B, n/2, 0, n, n/2)
			var B22 = obtenerSubmatriz(B, n/2, n/2, n, n)
			var S1 = sumaMatricesCuadradas(B12, B22, n/2, false)
			var S2 = sumaMatricesCuadradas(A11, A12, n/2, true)
			var S3 = sumaMatricesCuadradas(A21, A22, n/2, true)
			var S4 = sumaMatricesCuadradas(B21, B11, n/2, false)
			var S5 = sumaMatricesCuadradas(A11, A22, n/2, true)
			var S6 = sumaMatricesCuadradas(B11, B22, n/2, true)
			var S7 = sumaMatricesCuadradas(A12, A22, n/2, false)
			var S8 = sumaMatricesCuadradas(B21, B22, n/2, true)
			var S9 = sumaMatricesCuadradas(A11, A21, n/2, false)
			var S10 = sumaMatricesCuadradas(B11, B12, n/2, true)
			var P1 = multiplicacionStrassen(A11, S1, n/2)
			var P2 = multiplicacionStrassen(S2, B22, n/2)
			var P3 = multiplicacionStrassen(S3, B11, n/2)
			var P4 = multiplicacionStrassen(A22, S4, n/2)
			var P5 = multiplicacionStrassen(S5, S6, n/2)
			var P6 = multiplicacionStrassen(S7, S8, n/2)
			var P7 = multiplicacionStrassen(S9, S10, n/2)
			var TMP = sumaMatricesCuadradas(P5, P4, n/2, true)
			var TMP2 = sumaMatricesCuadradas(P6, P2, n/2, false)
			var C11 = sumaMatricesCuadradas(TMP, TMP2, n/2, true)
			var C12 = sumaMatricesCuadradas(P1, P2, n/2, true)
			var C21 = sumaMatricesCuadradas(P3, P4, n/2, true)
			TMP = sumaMatricesCuadradas(P5, P1, n/2, true)
			TMP2 = sumaMatricesCuadradas(P3, P7, n/2, true)
			var C22 = sumaMatricesCuadradas(TMP, TMP2, n/2, false)
			asignarSubmatriz(C, C11, 0, 0)
			asignarSubmatriz(C, C12, 0, n/2)
			asignarSubmatriz(C, C21, n/2, 0)
			asignarSubmatriz(C, C22, n/2, n/2)
			return C
		} else {
			var nuevaA = ajustarDimensionesMatriz(A,N)
			var nuevaB = ajustarDimensionesMatriz(B,N)
			var n = nuevaA.size
			var C = Array(n){Array(n,{0})}
			var A11 = obtenerSubmatriz(nuevaA, 0, 0, n/2, n/2)
			var A12 = obtenerSubmatriz(nuevaA, 0, n/2, n/2, n)
			var A21 = obtenerSubmatriz(nuevaA, n/2, 0, n, n/2)
			var A22 = obtenerSubmatriz(nuevaA, n/2, n/2, n, n)
			var B11 = obtenerSubmatriz(nuevaB, 0, 0, n/2, n/2)
			var B12 = obtenerSubmatriz(nuevaB, 0, n/2, n/2, n)
			var B21 = obtenerSubmatriz(nuevaB, n/2, 0, n, n/2)
			var B22 = obtenerSubmatriz(nuevaB, n/2, n/2, n, n)
			var S1 = sumaMatricesCuadradas(B12, B22, n/2, false)
			var S2 = sumaMatricesCuadradas(A11, A12, n/2, true)
			var S3 = sumaMatricesCuadradas(A21, A22, n/2, true)
			var S4 = sumaMatricesCuadradas(B21, B11, n/2, false)
			var S5 = sumaMatricesCuadradas(A11, A22, n/2, true)
			var S6 = sumaMatricesCuadradas(B11, B22, n/2, true)
			var S7 = sumaMatricesCuadradas(A12, A22, n/2, false)
			var S8 = sumaMatricesCuadradas(B21, B22, n/2, true)
			var S9 = sumaMatricesCuadradas(A11, A21, n/2, false)
			var S10 = sumaMatricesCuadradas(B11, B12, n/2, true)
			var P1 = multiplicacionStrassen(A11, S1, n/2)
			var P2 = multiplicacionStrassen(S2, B22, n/2)
			var P3 = multiplicacionStrassen(S3, B11, n/2)
			var P4 = multiplicacionStrassen(A22, S4, n/2)
			var P5 = multiplicacionStrassen(S5, S6, n/2)
			var P6 = multiplicacionStrassen(S7, S8, n/2)
			var P7 = multiplicacionStrassen(S9, S10, n/2)
			var TMP = sumaMatricesCuadradas(P5, P4, n/2, true)
			var TMP2 = sumaMatricesCuadradas(P6, P2, n/2, false)
			var C11 = sumaMatricesCuadradas(TMP, TMP2, n/2, true)
			var C12 = sumaMatricesCuadradas(P1, P2, n/2, true)
			var C21 = sumaMatricesCuadradas(P3, P4, n/2, true)
			TMP = sumaMatricesCuadradas(P5, P1, n/2, true)
			TMP2 = sumaMatricesCuadradas(P3, P7, n/2, true)
			var C22 = sumaMatricesCuadradas(TMP, TMP2, n/2, false)
			asignarSubmatriz(C, C11, 0, 0)
			asignarSubmatriz(C, C12, 0, n/2)
			asignarSubmatriz(C, C21, n/2, 0)
			asignarSubmatriz(C, C22, n/2, n/2)
			var productoC = obtenerSubmatriz(C,0,0,N,N)
			return productoC
		}		
	}
}

// Función para sumar dos matrices cuadradas
fun sumaMatricesCuadradas(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int, b: Boolean): Array<Array<Int>> {
	var D = Array(N){Array(N,{0})}
	for (i in 0 until N) {
		for (j in 0 until N) {
			if(b == true){
				D[i][j] = A[i][j] + B[i][j]
			} else {
				D[i][j] = A[i][j] - B[i][j]
			}
		}
	}
	return D
}

// Función para verificar si un entero es potencia de 2
fun esPotenciaDeDos(N: Int): Boolean {
	var logaritmoBase2DeN = Math.log(N.toDouble())/Math.log(2.0)
	var parteEntera = logaritmoBase2DeN.toInt()
	var diferencia = logaritmoBase2DeN - parteEntera
	if (diferencia == 0.0) {
		return true
	} else {
		return false 
	}
}

/*
 * Función para extender una matriz de dimensión N a una matriz de dimensión n, 
 * con n la potencia de 2 más cercana y mayor o igual a N
 */
fun ajustarDimensionesMatriz(A: Array<Array<Int>>, N: Int): Array<Array<Int>> {
	// Se obtiene el logaritmo base 2 de N
	var logaritmoBase2DeN = Math.log(N.toDouble())/Math.log(2.0)
	// Se obtiene el exponente x tal que 2^(x-1) <= N <= 2^x 
	var nuevoExponente = logaritmoBase2DeN.toInt() + 1
	// Se obtiene la nueva dimensión potencia de dos, mayor o igual que N 
	var nuevaDimension = (Math.pow(2.0, nuevoExponente.toDouble())).toInt()
	// Se inicializa la nueva matriz D 
	var D = Array(nuevaDimension){Array(nuevaDimension, {0})}
	// Se asigna A como una submatriz principal superior de dimensión N, en D
	asignarSubmatriz(D,A,0,0)
	return D 
}

// Función que retorna una submatriz desde A[filaInicio][columnaInicio] hasta A[filaFinal][columnaFinal], de una matriz A dada
fun obtenerSubmatriz(A: Array<Array<Int>>, filaInicio: Int, columnaInicio: Int, filaFinal: Int, columnaFinal: Int): Array<Array<Int>> {
	var filas = filaFinal - filaInicio
	var columnas = columnaFinal - columnaInicio
	var D = Array(filas){Array(columnas,{0})}
	for (i in 0 until filas) {
		for (j in 0 until columnas) {
			D[i][j] = A[filaInicio+i][columnaInicio+j]
		}
	}
	return D
}

// Función que asigna una submatriz B en una matriz A
fun asignarSubmatriz(C: Array<Array<Int>>, D: Array<Array<Int>>, filaInicio: Int, columnaInicio: Int) {
	for (i in 0 until D.size) {
		for (j in 0 until D[0].size) {
			C[i+filaInicio][j+columnaInicio] = D[i][j] 
		}
	}
}

// Para probar el programa:

// Función Strassen para potencias de 2
fun multiplicacionStrassen2(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Array<Array<Int>> {
	var C = Array(N){Array(N,{0})}
	if (N == 1) {
		C[0][0] = A[0][0]*B[0][0]
	} else {
		var A11 = obtenerSubmatriz(A, 0, 0, N/2, N/2)
		var A12 = obtenerSubmatriz(A, 0, N/2, N/2, N)
		var A21 = obtenerSubmatriz(A, N/2, 0, N, N/2)
		var A22 = obtenerSubmatriz(A, N/2, N/2, N, N)
		var B11 = obtenerSubmatriz(B, 0, 0, N/2, N/2)
		var B12 = obtenerSubmatriz(B, 0, N/2, N/2, N)
		var B21 = obtenerSubmatriz(B, N/2, 0, N, N/2)
		var B22 = obtenerSubmatriz(B, N/2, N/2, N, N)
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
		var P1 = multiplicacionStrassen2(A11, S1, N/2)
		var P2 = multiplicacionStrassen2(S2, B22, N/2)
		var P3 = multiplicacionStrassen2(S3, B11, N/2)
		var P4 = multiplicacionStrassen2(A22, S4, N/2)
		var P5 = multiplicacionStrassen2(S5, S6, N/2)
		var P6 = multiplicacionStrassen2(S7, S8, N/2)
		var P7 = multiplicacionStrassen2(S9, S10, N/2)
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

fun imprimirMatriz(A: Array<Array<Int>>) {
	for (i in 0 until A.size) {
		for (j in 0 until A[i].size) {
			print("${A[i][j]} ")
		}
		println("")
	}
	println("")
}

fun generadorMatriz(n: Int): Array<Array<Int>> {
    var A = Array(n){Array(n){0}}
    for (i in 0 until A.size) {
        for (j in 0 until (A[i].size)) {
            A[i][j] = (0..9).random()
        }
    }
    return A
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

fun medirTiempoStrassen2(A: Array<Array<Int>>, B: Array<Array<Int>>, N: Int): Double {
	var tInicio: Long = System.currentTimeMillis()
	multiplicacionStrassen2(A,B,N)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun medirTiempoAjustar(A: Array<Array<Int>>): Double {
	var tInicio: Long = System.currentTimeMillis()
	ajustarDimensionesMatriz(A, A.size)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun medirTiempoObtener(A: Array<Array<Int>>): Double {
	var tInicio: Long = System.currentTimeMillis()
	obtenerSubmatriz(A, 0, 0, A.size/2, A.size/2)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun medirTiempoAsignar(A: Array<Array<Int>>): Double {
	var B = obtenerSubmatriz(A, 0, 0, A.size/2, A.size/2)
	var tInicio: Long = System.currentTimeMillis()
	asignarSubmatriz(A,B,0,0)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun main(args: Array<String>) {
	var N = args[0].toInt()
	
	var A = generadorMatriz(N)
	var B = generadorMatriz(N)
	/*println("La matriz A es")
	imprimirMatriz(A)
	println("La matriz B es")
	imprimirMatriz(B)*/

	//var A = Array(N){Array(N,{1})}
	//var B = Array(N){Array(N,{2})}

	// Medir el tiempo de la multiplicación simple, de Stranssen y de Stranssen 2
	
	var tiempoSimple = medirTiempoSimple(A,B,N)
	println("Tiempo simple: ${tiempoSimple}")
	
	var tiempoStrassen = medirTiempoStrassen(A,B,N)
	println("Tiempo Stranssen: ${tiempoStrassen}")

	//var tiempoStrassen2 = medirTiempoStrassen2(A,B,N)
	//println("Tiempo Stranssen para potencias de 2: ${tiempoStrassen2}")

	//Midiendo el tiempo de las funciones extra en Strassen:
	
	/*var tiempoDimensiones = medirTiempoAjustar(A)
	var tiempoObtener = medirTiempoObtener(A)
	var tiempoAsignar = medirTiempoAsignar(A)
	println("Tiempo ajustar dimensiones: ${tiempoDimensiones}. Tiempo obtener una submatriz ${tiempoObtener}. Tiempo asignar una submatriz ${tiempoAsignar}")
	*/

	// Otras pruebas:	

	// Calculando y comparando los productos
	var simpleC = multiplicacionSimpleDeMatrices(A,B,N)
	//println("La matriz producto de la multiplicación simple es")
	//imprimirMatriz(simpleC)

	var strassenC = multiplicacionStrassen(A,B,N)
	//println("La matriz producto de Strassen es")
	//imprimirMatriz(strassenC)
	
	if (simpleC.contentDeepEquals(strassenC) == true) {
		println("Ambos productos son iguales")
	} else {
		println("Los productos no son iguales")
	}
	
	/* Probando algunas funciones:
	var b = esPotenciaDeDos(N)
	println("${N} es potencia de 2 es: ${b}")
	println("La matriz A redimensionada es")
	var nuevaA = ajustarDimensionesMatriz(A,N,b)
	imprimirMatriz(nuevaA)
	println("Su dimensión es ${nuevaA.size}")
	println("La matriz B redimensionada es")
	var nuevaB = ajustarDimensionesMatriz(B,N,b)
	imprimirMatriz(nuevaB)
	println("Su dimensión es ${nuevaB.size}")
	
	var A11 = obtenerSubmatriz(A, 0, 0, N/2, N/2)
	println("La matriz A11 es")
	imprimirMatriz(A11)

	asignarSubmatriz(A,A11,N/2,N/2)
	println("La matriz A reasignada es")
	imprimirMatriz(A)*/
	
	
	/* Un error que me salió una vez al comparar los productos, pero no salió de nuevo:
	var A2 = arrayOf(arrayOf(8,2,3,9,6), arrayOf(4,9,8,1,7), arrayOf(8,2,0,6,1), arrayOf(8,6,6,6,3), arrayOf(2,9,8,9,2))
	println("A2")
	imprimirMatriz(A2)
	var B2 = arrayOf(arrayOf(0,7,3,5,7), arrayOf(0,2,4,5,8), arrayOf(6,2,4,0,3), arrayOf(1,7,1,1,3), arrayOf(6,8,9,9,1))
	println("B2")
	imprimirMatriz(B2)
	var simpleC2 = multiplicacionSimpleDeMatrices(A2,B2,5)
	println("Producto simple")
	imprimirMatriz(simpleC2)
	var strassenC2 = multiplicacionStrassen(A2,B2,5)
	println("Producto strassen")
	imprimirMatriz(strassenC2)
	if (simpleC2.contentDeepEquals(strassenC2) == true) {
		println("Ambos productos son iguales")
	} else {
		println("Los productos no son iguales")
	}*/
}
fun smoothsort(m: Array<Int>) {
	var N = m.size
	var aux = Array(8, {0}) // Herramienta auxiliar
	aux[0] = 1 // Corresponde a q := 1
	aux[1] = 0 // Corresponde a r := 0
	aux[2] = 1 // Corresponde a p := 1
	aux[3] = 1 // Corresponde a b := 1
	aux[4] = 1 // Corresponde a c := 1
	aux[5] = 0 // Corresponde a r1, inicializando
	aux[6] = 0 // Corresponde a b1, inicializando
	aux[7] = 0 // Corresponde a c1, inicializando
	while (aux[0] != N) { // do q != N ->
		aux[5] = aux[1] // r1 := r
		if (aux[2]%8 == 3) { // if p mod 8 = 3 ->
			aux[6] = aux[3] // b1 := b
			aux[7] = aux[4] // c1 := c
			sift(m,aux) // sift
			aux[2] = (aux[2]+1)/4 // p := (p+1)/4
			up(aux) // up
			up(aux) // up
		} else if (aux[2]%4 == 1) { // [] p mod 4 = 1 ->
			if ((aux[0] + aux[4]) < N) { // if q + c < N ->
				aux[6] = aux[3] // b1 := b
				aux[7] = aux[4] // c1 := c
				sift(m, aux) // sift
			} else { // [] q + c >= N ->
				trinkle(m,aux) // trinkle
			}
			down(aux) // down
			aux[2] = 2*aux[2] // p := 2*p
			while (aux[3] != 1) { // do b != 1 ->
				down(aux) // down
				aux[2] = 2*aux[2] // p := 2*p
			}
			aux[2] = aux[2] + 1 // p := p + 1 
		}
		aux[0] = aux[0] + 1 // q := q + 1
		aux[1] = aux[1] + 1 // r := r+1
	}
	aux[5] = aux[1] // r1 := r
	trinkle(m,aux) // trinkle
	while (aux[0] != 1) { // do q != 1 -> 
		aux[0] = aux[0] - 1 // q := q-1
		if (aux[3] == 1) { // if b = 1 ->
			aux[1] = aux[1] - 1 // r := r-1
			aux[2] = aux[2] - 1 // p := p-1
			while (even(aux[2]) == true) { // do even(p) ->
				aux[2] = aux[2]/2 // p := p/2
				up(aux) // up
			}
		} else if (aux[3] >= 3) { // [] b >= 3
			aux[2] = aux[2] - 1 // p := p - 1
			aux[1] = aux[1] - aux[3] + aux[4] // r := r - b + c
			if (aux[2] > 0) { // [] p > 0 ->
				semitrinkle(m, aux) // semitrinkle
			}
			down(aux) // down
			aux[2] = 2*aux[2] + 1 // p := 2*p + 1
			aux[1] = aux[1] + aux[4] // r := r + c
			semitrinkle(m,aux) // semitrinkle
			down(aux) // down
			aux[2] = 2*aux[2] + 1 // p := 2*p + 1
		}
	}
}

fun up(aux: Array<Int>) { // b,c := b+c+1, b
	var tmp = aux[3]
	aux[3] = aux[3] + aux[4] + 1
	aux[4] = tmp
}

fun down(aux: Array<Int>) { // b,c := c, b-c-1
	var tmp = aux[4]
	aux[4] = aux[3] - aux[4] - 1
	aux[3] = tmp
}

fun up1(aux: Array<Int>) { // b1, c1 = b1 + c1 + 1, b1
	var tmp = aux[6]
	aux[6] = aux[6] + aux[7] + 1
	aux[7] = tmp
}

fun down1(aux: Array<Int>) { // b1, c1 = c1, b1 - c1 - 1
	var tmp = aux[7]
	aux[7] = aux[6] - aux[7] - 1
	aux[6] = tmp
}

fun sift(m: Array<Int>, aux: Array<Int>) {
	while(aux[6] >= 3) { // do b1 >= 3 ->
		var r2 = aux[5] - aux[6] + aux[7] // r2 := r1 - b1 + c1
		if(m[r2] <= m[aux[5]-1]) { // [] m(r2) <= m(r1-1) 
			r2 = aux[5] - 1 // r2 := r1 -1
			down1(aux) // down1
		}
		if (m[aux[5]] >= m[r2]) { // if m(r1) >= m(r2) ->
			aux[6] = 1 // b1 := 1
		} else { // [] m(r1) < m(r2) ->
			swap(m, aux[5], r2) // m:swap(r1,r2)
			aux[5] = r2 // r1 := r2
			down1(aux) // down1
		}
	}
}

fun trinkle(m: Array<Int>, aux: Array<Int>) {
	var p1 = aux[2] // p1 := p
	aux[6] = aux[3] // b1 := b
	aux[7] = aux[4] // c1 := c
	while (p1 > 0) { // do p1 > 0 ->
		while (even(p1) == true) { // do even(p1) ->
			p1 = p1/2 // p1 := p1/2
			up1(aux) // up1
		}
		var r3 = aux[5] - aux[6] // r3 := r1 - b1 
		if (p1 == 1 || m[r3] <= m[aux[5]]) { // p1 = 1 cor m(r3) <= m(r1) ->
			p1 = 0 // p1 = 0
		} else if (p1 > 1 && m[r3] > m[aux[5]]) { // p1 cand m(r3) > m(r1)
			p1 = p1 - 1 // p1 := p1 - 1
			if (aux[6] == 1) { // if b1 = 1 ->
				swap(m, aux[5], r3) // m: swap(r1, r3)
				aux[5] = r3 // r1 := r3
			} else if (aux[6] >= 3) { // [] b1 >= 3 ->
				var r2 = aux[5] - aux[6] + aux[7] // r2 := r1 - b1 + c1
				if(m[r2] <= m[aux[5]-1]) { // [] m(r2) <= m(r1-1)
					r2 = aux[5] -1 // r2 := r1 - 1
					down1(aux) //down1
					p1 = 2*p1 //p1 = 2*p1
				}
				if (m[r3] >= m[r2]) { // if m(r3) >= m(r2)
					swap(m, aux[5], r3) // m:swap(r1,r3)
					aux[5] = r3 // r1 := r3
				} else { // [] m(r3) < m(r2)
					swap(m, aux[5], r2) // m:swap(r1,r2)
					aux[5] = r2 // r1 := r2
					down1(aux) // down1
					p1 = 0 // p1 := 0
				}
			}
		}
	}
	sift(m, aux) // sift
}

fun even(p1: Int): Boolean {
	if(p1%2 == 0) {
		return true
	} else {
		return false
	}
}

fun semitrinkle(m: Array<Int>, aux: Array<Int>) {
	aux[5] = aux[1] - aux[4] // r1 := r-c
	if (m[aux[5]] > m[aux[1]]) { // [] m(r1) > m(r) ->
		swap(m,aux[1],aux[5]) // m: swap(r,r1)
		trinkle(m, aux) // trinkle
	}
}

// Lo que sigue es para probar el programa

fun main(args: Array<String>) {
	var N = args[0].toInt()
	var m = Array(N,{0})
	for (i in 0 until N) {
		m[i] = (0..900000).random()
	}
	println("Se tiene m")
	//imprimiArreglo(m)
	if(estaEnOrdenAscendente(m) == false) {
		println("Un arreglo desordenado")
	} else {
		println("Un arreglo ordenado")
	}
	var tiempo = medirTiempo(m)
	println("Se aplica Smoothsort y se obtiene")
	//imprimirArreglo(m)
	if(estaEnOrdenAscendente(m) == false) {
		println("Un arreglo desordenado")
	} else {
		println("Un arreglo ordenado")
	}
	println("En ${tiempo} segundos")

}


fun swap (A: Array<Int>,x: Int, y: Int) {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
}

fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}

fun medirTiempo(A: Array<Int>): Double {
	var tInicio: Long = System.currentTimeMillis()
	smoothsort(A)
	var tFinal: Long = System.currentTimeMillis()
	var tiempo: Double = ((tFinal- tInicio)/1000.0).toDouble()
	return tiempo
}

fun imprimiArreglo(A: Array<Int>) {
	for (i in 0 until A.size) {
		print("${A[i]} ")
	}
	println("")
}
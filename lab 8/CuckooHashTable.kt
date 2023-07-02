import kotlin.math.roundToInt

class CuckooHashTable{

	var n = 7
	var totalElementos = 0
	var factorDeCarga: Double = (totalElementos.toDouble())/(n.toDouble())
	var conocidas: Array<Int?> = Array(2*n, {null})
	var tabla1 = Array(n, {CuckooHashTableEntry(null, null)})
	var tabla2 = Array(n, {CuckooHashTableEntry(null, null)})
	val A = 0.6180339887

	// Procedimientos del TAD Diccionario

	// Método para agregar una clave y un valor al diccionario
	fun agregar(k: Int, texto: String) {
		if (buscar(k) != null) {
			return
		}
		var x = CuckooHashTableEntry(k,texto)
		for (i in 0 until n/3) {
			var j = funcionDeHash1(x.clave!!)
			var tmp1 = tabla1[j]
			tabla1[j] = x
			conocidas[j] = x.clave
			x = tmp1
			if (x.clave == null) {
				totalElementos++
				ajustarTabla()
				return
			}
			j = funcionDeHash2(x.clave!!)
			var tmp2 = tabla2[j]
			tabla2[j] = x
			conocidas[j+n] = x.clave
			x = tmp2
			if (x.clave == null) {
				totalElementos++
				ajustarTabla()
				return
			}
		}
		rehashing(n, tabla1, tabla2)
		agregar(k, texto)
	}

	// Método para eliminar una clave dada de un TAD diccionario
	fun eliminar(k: Int) {
		var h1 = funcionDeHash1(k)
		if (tabla1[h1].clave == k){
			tabla1[h1] = CuckooHashTableEntry(null, null)
			conocidas[h1] = null
			totalElementos--
			ajustarTabla()
			return
		}
		var h2 = funcionDeHash2(k)
		if (tabla2[h2].clave == k){
			tabla2[h2] = CuckooHashTableEntry(null, null)
			conocidas[h2+n] = null
			totalElementos--
			ajustarTabla()
			return
		}
	}

	// Método para buscar una clave en un TAD diccionario 
	fun buscar(k: Int): String? {
		var h1 = funcionDeHash1(k)
		var h2 = funcionDeHash2(k)
		if (tabla1[h1].clave == k) {
			return tabla1[h1].valor
		}
		if (tabla2[h2].clave == k) {
			return tabla2[h2].valor
		}
		return null
	}

	// Método para verificar si una clave dada pertenece a un TAD diccionario
	fun existe(k: Int): Boolean {
		var valor = buscar(k)
		if (valor != null) {
			return true
		}
		return false
	}

	// Método para convertir la tabla de un TAD diccionario en un String
	override fun toString(): String {
		var valores = ""
		for (i in 0 until n) {
			if (tabla1[i].clave != null) {
				valores = valores + " (${tabla1[i].clave}, ${tabla1[i].valor}) "
			}
			if (tabla2[i].clave != null) {
				valores = valores + " (${tabla2[i].clave}, ${tabla2[i].valor}) "
			}
		}
		return valores
	}

	// Método para obtener el número de elementos de un TAD diccionario
	fun numElementos(): Int {
		return totalElementos
	}

	// Procedimientos para la tabla de Hash y adicionales para el TAD diccionario

	// Método correspondiente a la función de Hash división 
	fun funcionDeHash1(k: Int): Int {
		return k%n
	}

	// Método correspondiente a la función de Hash multiplicación 
	fun funcionDeHash2(k: Int): Int {
		var h = (n*((k*A)%1)).toInt()
		return h
	}

	// Método para ajustar el factor de carga y, en caso que aplique, hacer rehashing
	fun ajustarTabla() {
		factorDeCarga = (totalElementos.toDouble())/(n.toDouble())
		if (factorDeCarga >= 0.7) {
			rehashing(n, tabla1, tabla2)
		}
	}

	// Método para obtener el nuevo tamaño de la tabla de Hash para el rehashing
	fun incr(n: Int): Int {
		return ((n+16)*1.5).roundToInt()
	}	

	// Método para hacer rehashing de la tabla de Hash
	fun rehashing(n1: Int, tablaA: Array<CuckooHashTableEntry>, tablaB: Array<CuckooHashTableEntry>) {
		var nuevoN = incr(n1)
		n = nuevoN
		var conocidasNueva: Array<Int?> = Array(2*n, {null})
		var tablaNueva1 = Array(n, {CuckooHashTableEntry(null, null)})
		var tablaNueva2 = Array(n, {CuckooHashTableEntry(null, null)})
		for (i in 0 until n1) {
			if (tablaA[i].clave != null) {
				var j = funcionDeHash1(tablaA[i].clave!!)
				tablaNueva1[j] = tablaA[i]
				conocidasNueva[j] = tablaA[i].clave!!
			}
			if (tablaB[i].clave != null) {
				var j = funcionDeHash2(tablaB[i].clave!!)
				tablaNueva2[j] = tablaB[i]
				conocidasNueva[j+n] = tablaB[i].clave!!
			}
		}
		conocidas = conocidasNueva
		tabla1 = tablaNueva1
		tabla2 = tablaNueva2
	}

	fun conocidasToString(): String {
		var valores = ""
		for (i in 0 until n) {
			if (conocidas[i] != null) {
				valores = valores + " ${conocidas[i]} "
			}
			if (conocidas[i+n] != null) {
				valores = valores + " ${conocidas[i+n]} "
			}
		}
		return valores 
	}
}
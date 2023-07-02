import kotlin.math.roundToInt

class HashTableChaining {

	var n = 7
	var totalElementos = 0
	var factorDeCarga: Double = (totalElementos.toDouble())/(n.toDouble())
	var conocidas = ListaCircular()
	var tabla = Array(n,{CircularList()})

	// Procedimientos del TAD Diccionario

	// Método para agregar una clave y un valor al diccionario
	fun agregar(k: Int, texto: String) {
		var x = HashTableEntry(k,texto)
		var i = funcionDeHash(k)
		tabla[i].agregarAlFrente(x)
		totalElementos++
		conocidas.agregarAlFrente(k)
		ajustarTabla()
		
	}

	// Método para eliminar una clave dada de un TAD diccionario
	fun eliminar(k: Int) {
		var i = funcionDeHash(k)
		var x = tabla[i].buscar(k)
		if (x != null) {
			tabla[i].eliminar(x)
			totalElementos--
			var nodoTmp = conocidas.buscar(k)
			conocidas.eliminar(nodoTmp)
			ajustarTabla()	
		}
	}

	// Método para buscar una clave en un TAD diccionario 
	fun buscar(k: Int): String? {
		var i = funcionDeHash(k)
		var x = tabla[i].buscar(k)
		if (x != null) {
			return x.valor
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
			if (casillaVacia(i) == false) {
				valores = valores + tabla[i].toString()
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
	fun funcionDeHash(k: Int): Int {
		return k%n
	}

	// Método para ajustar el factor de carga y, en caso que aplique, hacer rehashing
	fun ajustarTabla() {
		factorDeCarga = (totalElementos.toDouble())/(n.toDouble())
		if (factorDeCarga >= 0.7) {
			rehashing(n, tabla)
		}
	}

	// Método para obtener el nuevo tamaño de la tabla de Hash para el rehashing
	fun incr(n: Int): Int {
		return ((n+16)*1.5).roundToInt()
	}	

	// Método para hacer rehashing de la tabla de Hash
	fun rehashing(n1: Int, tablaA: Array<CircularList>) {
		var nuevoN = incr(n1)
		n = nuevoN
		var tablaNueva = Array(nuevoN, {CircularList()})
		for (i in 0 until n1) {
			var y = tablaA[i].centi?.next
			while (y?.clave != null) {
				var x = y
				y = y.next 
				var j = funcionDeHash(x.clave!!)
				tablaNueva[j].agregarAlFrente(x)
			}
		}
		tabla = tablaNueva
	}

	// Método para verificar si una de las casillas de la tabla de Hash contiene una lista circular vacía
	fun casillaVacia(i: Int): Boolean {
		var primero: HashTableEntry? = tabla[i].centi?.next
		if (primero == null) {
			return true
		}
		return false
	}
}

// Clases auxiliares

class Nodo (var value: Int? = null) {
    
    var prev: Nodo? = null
    var next: Nodo? = null
}

class ListaCircular {
    
    var centi: Nodo? = Nodo()

    fun agregarAlFrente(k: Int) {
        val key = Nodo(k)
        var centinela = centi
        if (centinela?.next == null) {
            centinela?.next = key
            centinela?.prev = key 
            key.prev = centinela
            key.next = centinela
        } else {
            centinela.next?.prev = key
            key.next = centinela.next
            key.prev = centinela
            centinela.next = key
        }
    }

    fun agregarAlFinal(k: Int) {
        val key = Nodo(k)
        var centinela = centi
        if (centinela?.next == null) {
            agregarAlFrente(k)
        } else {
            centinela.prev?.next = key 
            key.prev = centinela.prev
            key.next = centinela
            centinela.prev = key
        }
    }

    fun buscar(value: Int): Nodo? {
        var x = centi?.next
        while (x?.value != value && x?.value != null) {
            x = x.next
        }
        if (x?.value == value) {
        	return x
        }
        return null
    }

    fun eliminar(key: Nodo?) {
        // El nodo ingresado debe pertenecer a la lista, en caso contrario no habrá cambios
        if (key?.value != null && key.prev != null && key.next != null) {
            key.next?.prev = key.prev
            key.prev?.next = key.next
   	    }
    }

    override fun toString(): String{
        var valores= ""
        var e = centi?.next
        while (e?.value != null) {
            valores = valores + " ${e.value} "
            e = e.next
        }
        return valores
    }
}
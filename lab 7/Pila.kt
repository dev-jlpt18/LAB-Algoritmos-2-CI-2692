class Pila(contenido: ListaCircular) {
	var secuencia: ListaCircular = contenido
		set(value) {
			field = value
		}
	
	fun empilar(e: Int) {
		var x = Nodo(e)
		secuencia.agregarAlFinal(x)
	}

	fun desempilar() {
		var x = secuencia.Centinela.prev
		secuencia.eliminar(x)
	}

	fun tope(): Int {
		var x = secuencia.Centinela.prev
		var tope = x.key
		if (tope == null) {
			println("La pila está vacía. Por defecto, se devuelve 0 como tope")
			return 0
		} else {
			return tope
		}
	}

	fun estaVacia(): Boolean{
		var numeroElementos = secuencia.numeroNodos
		if (numeroElementos <= 0) {
			return true
		} else {
			return false
		}
	}

	override fun toString(): String{
		var rep = secuencia.toString()
		return rep
	}
}
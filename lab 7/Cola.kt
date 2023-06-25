class Cola(contenido: ListaCircular) {
	var secuencia: ListaCircular = contenido
		set(value) {
			field = value
		}
	
	fun encolar(e: Int) {
		var x = Nodo(e)
		secuencia.agregarAlFinal(x)
	}

	fun desencolar() {
		var x = secuencia.Centinela.next
		secuencia.eliminar(x)
	}

	fun primero(): Int {
		var x = secuencia.Centinela.next
		var primero = x.key
		if (primero == null) {
			println("La cola está vacía. Por defecto, se devuelve 0 como primer elemento")
			return 0
		} else {
			return primero
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
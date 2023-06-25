class ListaCircular {
	
	var Centinela: Nodo = Nodo(null)
	var numeroNodos = 0
	
	init {
		Centinela.prev = Centinela
		Centinela.next = Centinela
	}

	fun agregarAlFrente(k: Int) {
		var x = Nodo(k)
		x.next = Centinela.next
		x.prev = Centinela
		Centinela.next.prev = x
		Centinela.next = x
		numeroNodos = numeroNodos + 1 
	}

	fun agregarAlFinal(k: Int) {
		var x = Nodo(k)
		x.next = Centinela
		x.prev = Centinela.prev
		Centinela.prev.next = x
		Centinela.prev = x
		numeroNodos = numeroNodos + 1 
	}

	fun buscar(k: Int): Nodo? {
		Centinela.key = k
		var x = Centinela.next
		while(x.key != k) {
			x = x.next 
		}
		Centinela.key = null
		if (x == Centinela) {
			return null
		}
		return x
	}

	fun eliminar(x: Nodo) {
		if (x.estaInicializado() == true && x.prev != x && x.next != x) {
			x.prev.next = x.next
			x.next.prev = x.prev
			numeroNodos = numeroNodos - 1
			// El nodo eliminado se convierte en un "nodo solitario" que se llama a sí mismo
			x.prev = x
			x.next = x
		}
	}

	override fun toString(): String {
		var lista = ""
		var x = Centinela.next
		while(x.key != null) {
			lista = lista+" ${x.key} "
			x = x.next 
		}
		return lista 
	}

	fun buscarYEliminar(k: Int) {
		var y = buscar(k)
		if(y != null) {
			eliminar(y)
		} 
	}
}
class Nodo(clave: Int?){

	lateinit var prev: Nodo
	lateinit var next: Nodo
	
	var key = clave 
		set(value) {
			field = value
		}

	fun estaInicializado(): Boolean {
		var tmp1 = this::prev.isInitialized
		var tmp2 = this::next.isInitialized
		var tmp3 = tmp2 && tmp1
		return tmp3
	}

	override fun toString(): String {
		return "${key}"
	}
}
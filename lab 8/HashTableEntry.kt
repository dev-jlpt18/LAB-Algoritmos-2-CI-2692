class HashTableEntry(var key: Int?, var texto: String?) {
	var clave = key
		set(value){
			field = value
		}

	var valor = texto 
		set(value){
			field = value
		}

	var prev: HashTableEntry? = null
	var next: HashTableEntry? = null
}
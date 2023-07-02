class CircularList {
    
    var centi: HashTableEntry? = HashTableEntry(null, null)

    fun agregarAlFrente(key: HashTableEntry) {
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

    fun agregarAlFinal(key: HashTableEntry) {
        var centinela = centi
        if (centinela?.next == null) {
            agregarAlFrente(key)
        } else {
            centinela.prev?.next = key 
            key.prev = centinela.prev
            key.next = centinela
            centinela.prev = key
        }
    }

    fun buscar(k: Int): HashTableEntry? {
        var x = centi?.next
        while (x?.clave != k && x?.clave != null) {
            x = x.next
        }
        if (x?.clave == k) {
        	return x
        }
        return null
    }

    fun eliminar(key: HashTableEntry) {
        if (key.clave != null && key.prev != null && key.next != null) {
            key.next?.prev = key.prev
            key.prev?.next = key.next
   	    }
    }

    override fun toString(): String {
        var valores = ""
        var e = centi?.next
        while (e?.clave != null) {
            valores = valores + " (${e.clave}, ${e.valor}) "
            e = e.next
        }
        return valores
    }
}
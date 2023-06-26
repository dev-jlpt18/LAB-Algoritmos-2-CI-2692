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

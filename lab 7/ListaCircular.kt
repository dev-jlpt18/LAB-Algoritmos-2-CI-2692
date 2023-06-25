class ListaCircular(var lista: Nodo? = Nodo()) {
    fun agregarAlFrente(k: Int) {
        val key = Nodo(k)
        var centinela = lista
        if (centinela?.next == null) {
            centinela?.next = key
            centinela?.prev = key 
            
            key.prev = centinela
            key.next = centinela
        } else {
        	var firts = centinela.next
            first?.prev = key
            
            key.next = first
            key.prev = centinela

            centinela.next = key
        }
    }
    fun agregarAlFinal(k Int) {
        val key = Nodo(k)
        var centinela = lista

        if (centinela?.next == null) {
            agregarAlFrente(k)
        } else {
            var last = centinela.prev
            last?.next = key 

            key.prev = last
            key.next = centinela

            centinela.prev = key
        }
    }
    fun buscar(value: Int): Nodo? {
        var x = lista?.next
        while (x?.value != value && x?.value != null) {
            x = x?.next
        }
        if (x?.value == value) {
        	return x
        }
        return null
    }
    fun eliminar(key: Nodo?) {
        if (key?.value != null && key?.prev != null && key?.next != null) 		{
            var ladoDerecho: Nodo? = key.next
            var ladoIzquierdo: Nodo? = key.prev
            ladoDerecho?.prev = ladoIzquierdo
            ladoIzquierdo?.next = ladoDerecho
   	    }
    }
    fun printValues() {
        var e = lista?.next
        while (e?.value != null) {
            println("${e.value}")
            e = e.next
        }
    }
}

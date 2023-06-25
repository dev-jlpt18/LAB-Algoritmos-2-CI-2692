class ListaCircular(var lista: Nodo? = Nodo()) {
    fun agregarAlFrente(value: Int) {
        val key = Nodo(value)
        var centinela = lista
        if (centinela?.next == null) {
            centinela?.next = key
            centinela?.prev = key 
            
            key.prev = centinela
            key.next = centinela
        } else {
            key.next = centinela.next
            key.prev = centinela

            centinela.next = key
        }
    }
    fun agregarAlFinal(value: Int) {
        val key = Nodo(value)
        var centinela = lista

        if (centinela?.next == null) {
            agregarAlFrente(value)
        } else {
            var last = centinela.prev
            last?.next = key 

            key.prev = last
            key.next = centinela

            centinela.prev = key
        }
    }
    fun buscar(value: Int): Nodo? {
        var x = lista
        var y: Nodo?
        while (x?.next != this.lista) {
            y = x?.next
            if (y?.value == value) {
                return y
            }
            x = x?.next
        }
        return null
    }
    fun eliminar(key: Nodo?) {
        var x = lista
        var ladoDerecho: Nodo?
        var ladoIzquierdo: Nodo?
        while (x?.value != key?.value) {
            x = x?.next
        }
        ladoDerecho = x?.next
        ladoIzquierdo = x?.prev

        ladoDerecho?.prev = ladoIzquierdo
        ladoIzquierdo?.next = ladoDerecho
    }
}
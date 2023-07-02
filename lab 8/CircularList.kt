class CircularList {

    var firstSlot = HashTableEntry()
    var numberSlot = 0
 
    fun agregar(k: Int, valor: String) {
        // Creacion del nodo
        val slot = HashTableEntry(k, valor)

        // Se verifica si es una lista vacia
        if (firstSlot.clave == null && firstSlot.valor == null) {
            firstSlot.clave = slot.clave
            firstSlot.valor = slot.valor 
        } else if (firstSlot.clave != null && firstSlot.valor != null && firstSlot.next == null){
        	firstSlot.next = slot
            slot.prev = firstSlot
        } else {
            var secondSlot = firstSlot.next
            firstSlot.next = slot
            secondSlot?.prev = slot

            slot.prev = firstSlot
            slot.next = secondSlot
        }
        numberSlot++
    }

    fun buscar(clave: Int): HashTableEntry? {
        // Usamos el nodo de la lista
        var x: HashTableEntry? = firstSlot
        // Buscamos el nodo que sea igual al numero ingresado, es decir, que su key sea igual al valor
        while (x?.clave != null) {
            if (x.clave == clave) {
                numberSlot+=1
                return x
            } else {
                x = x.next
            }
        }
        // En caso contrario nos devuelve null
        return null
    }

    fun eliminar(elemento: HashTableEntry?) {
        if (elemento?.prev != null && elemento.next == null) {
            var y: HashTableEntry? = elemento.prev
            y?.next = elemento.next
   	    } else if (elemento?.prev == null && elemento?.next != null) {
        	var y: HashTableEntry? = elemento.next
        	firstSlot.clave = y?.clave
            firstSlot.valor = y?.valor
            firstSlot.next = y?.next
            eliminar(y)
        } else {
            var ladoDerecho = elemento?.next
            var ladoIzquierdo = elemento?.prev
            
            ladoDerecho?.prev = ladoIzquierdo
            ladoIzquierdo?.next = ladoDerecho
        }
        numberSlot--
    }
    fun printValues(): String{
        var valores= ""
        var e: HashTableEntry? = firstSlot
        while (e?.clave != null) {
            valores = valores + " ${e.valor} "
            e = e.next
        }
        println(valores)
        return valores
    }
}

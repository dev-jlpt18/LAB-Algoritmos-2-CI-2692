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
        while (x != null) {
            if (x.clave == clave) {
                numberSlot+=1
                return x
            } else {
                x = firstSlot.next
            }
        }
        // En caso contrario nos devuelve null
        return null
    }

    fun eliminar(elemento: HashTableEntry?) {
        // El nodo ingresado debe pertenecer a la lista, en ese caso no habra cambios en la lista circular
        // Se verifica que el nodo no tenga clave vacia, ni apuntadores vacios
        if (elemento?.prev != null && elemento.next == null) {
            var y: HashTableEntry? = elemento.prev
            y?.next = elemento.next
   	    } else if (elemento?.prev == null && elemento?.next != null) {
            var y: HashTableEntry? = elemento.next
            y?.prev = elemento.prev
        } else {
            var ladoDerecho = elemento?.next
            var ladoIzquierdo = elemento?.prev
            
            ladoDerecho?.prev = ladoIzquierdo
            ladoIzquierdo?.next = ladoDerecho
        }
        numberSlot--
    }
    fun printValues(): String{
        // Impresion de los valores de cada nodo, empezando por el primer nodo y se detiene en el bucle
        var valores= ""
        var e: HashTableEntry? = firstSlot
        while (e?.valor != null) {
            valores = valores + " ${e.valor} "
            e = e.next
        }
        return valores
    }
}

class CircularList {
    var id = 0;
    var firstSlot = HashTableEntry()
    var numberSlot = 0
 
    fun agregar(par: Pair<Int, String>) {
        // Creacion del nodo
        val slot = HashTableEntry(par)

        // Se verifica si es una lista vacia
        if (firstSlot.elemento.first == null) {
            firstSlot.elemento = slot.elemento 
        } else if (firstSlot.elemento.first != null && firstSlot.next == null){
        	firstSlot.next = slot
            slot.prev = firstSlot
        } else {
            var secondSlot = firstSlot.next
            firstSlot.next = slot
            secondSlot?.prev = slot

            slot.prev = firstSlot
            slot.next = secondSlot
        }
        numberSlot+=1
    }

    fun buscar(clave: Int): HashTableEntry? {
        // Usamos el nodo de la lista
        var x: HashTableEntry? = firstSlot
        // Buscamos el nodo que sea igual al numero ingresado, es decir, que su key sea igual al valor
        while (x != null) {
            if (x.elemento.first == clave) {
                numberSlot+=1
                return x
            } else {
                x = x.next
            }
        }
        // En caso contrario nos devuelve null
        return null
    }

    fun eliminar(clave: HashTableEntry?) {
        // El nodo ingresado debe pertenecer a la lista, en ese caso no habra cambios en la lista circular
        // Se verifica que el nodo no tenga clave vacia, ni apuntadores vacios
        if (clave?.prev != null && clave.next == null) {
            var y: HashTableEntry? = clave.prev
            y?.next = clave.next
   	    } else if (clave?.prev == null && clave?.next != null) {
            var y: HashTableEntry? = clave.next
            y?.prev = clave.prev
        } else {
            var ladoDerecho = clave?.next
            var ladoIzquierdo = clave?.prev
            
            ladoDerecho?.prev = ladoIzquierdo
            ladoIzquierdo?.next = ladoDerecho
        }
        numberSlot-=1
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
class HashTableEntry(var elemento: Pair<Int?, String?> = Pair(null,null)) {
    var prev: HashTableEntry? = null
    var next: HashTableEntry? = null
}
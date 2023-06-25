class ListaCircular(var lista: Nodo? = Nodo()) {
    fun agregarAlFrente(k: Int) {
        // Creacion del nodo
        val key = Nodo(k)
        // Creacion de Centinela para su modificacion
        var centinela = lista
        // Se verifica si es una lista vacia
        if (centinela?.next == null) {
            centinela?.next = key
            centinela?.prev = key 
            
            key.prev = centinela
            key.next = centinela
        } else {
            // Caso en donde no esta vacia
            // Creacion del primer elemento para modificar su apuntador
        	var firts = centinela.next
            first?.prev = key
            
            // Se procede a indicar los apuntadores del nodo que se agregara al frente
            key.next = first
            key.prev = centinela

            // Se arregla el apuntador del centinela
            centinela.next = key
        }
    }
    fun agregarAlFinal(k Int) {
        // Creacion del nodo
        val key = Nodo(k)
        // Creacion de Centinela para su modificacion
        var centinela = lista

        // Se verifica si es una lista vacia, en ese caso vamos a la funcion agregarAlInicio, pues es lo mismo
        if (centinela?.next == null) {
            agregarAlFrente(k)
        } else {
            // Caso en donde no esta vacia
            // Creacion del ultimo elemento para modificar su apuntador
            var last = centinela.prev
            last?.next = key 

            // Se procede a indicar los apuntadores del nodo que se agregara al frente
            key.prev = last
            key.next = centinela

            // Se arregla el apuntador del centinela
            centinela.prev = key
        }
    }
    fun buscar(value: Int): Nodo? {
        // Usamos el nodo de la lista
        var x = lista?.next
        // Buscamos el nodo que sea igual al numero ingresado, es decir, que su key sea igual al valor
        while (x?.value != value && x?.value != null) {
            x = x?.next
        }
        // Verificamos si el valor del nodo encontrado es igual al valor a buscado, en el caso que es cierto nos devuelve el nodo
        if (x?.value == value) {
        	return x
        }
        // En caso contrario nos devuelve null
        return null
    }
    fun eliminar(key: Nodo?) {
        // El nodo ingresado debe pertenecer a la lista, en ese caso no habra cambios en la lista circular
        // Se verifica que el nodo no tenga clave vacia, ni apuntadores vacios
        if (key?.value != null && key?.prev != null && key?.next != null) {
            // Creacion de variables que son nodos de los apuntadores del nodo ingresado
            var ladoDerecho: Nodo? = key.next
            var ladoIzquierdo: Nodo? = key.prev

            // Corregimos los apuntadores de los nodos para que apunten entre si
            // De esta forma se elimina el nodo ingresado
            ladoDerecho?.prev = ladoIzquierdo
            ladoIzquierdo?.next = ladoDerecho
   	    }
    }
    fun printValues() {
        // Impresion de los valores de cada nodo, empezando por el primer nodo y se detiene en el centinela
        var e = lista?.next
        while (e?.value != null) {
            println("${e.value}")
            e = e.next
        }
    }
}

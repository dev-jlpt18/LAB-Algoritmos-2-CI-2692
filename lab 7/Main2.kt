fun main() {

	// Probar la clase ListaCircular
	
	println("Se prueba la clase ListaCircular")
	println(" ")
	var lista1 = ListaCircular()
	println("Se crea una lista circular agregando, uno a uno, 5 enteros aleatorios")
	var k = (-1000..1000).random()
	println("El valor ${k} se agrega al frente y se obtiene la lista")
	lista1.agregarAlFrente(k)
	println(lista1.toString())
	k = (-1000..1000).random()
	println("El valor ${k} se agrega al frente y se obtiene la lista")
	lista1.agregarAlFrente(k)
	println(lista1.toString())
	k = 25
	println("El valor ${k} se agrega al final y se obtiene la lista")
	lista1.agregarAlFinal(k)
	println(lista1.toString())
	k = (-1000..1000).random()
	println("El valor ${k} se agrega al final y se obtiene la lista")
	lista1.agregarAlFinal(k)
	println(lista1.toString())
	k = (-1000..1000).random()
	println("El valor ${k} se agrega al frente y se obtiene la lista")
	lista1.agregarAlFrente(k)
	println(lista1.toString())
	k = 25
	println("Se busca el valor ${k} y se obtiene que")
	var busqueda = lista1.buscar(k)
	if (busqueda == null) {
		println("No se encuentra")
	} else {
		println("Si se encuentra")
		println("Luego, se elimina el nodo que tiene ${k} como clave y se obtiene la nueva lista")
		lista1.eliminar(busqueda)
		println(lista1.toString())
	}
	println("De nuevo, se busca la clave ${k} en la lista y se obtiene que")
	busqueda = lista1.buscar(k)
	if (busqueda == null) {
		println("Ya no se encuentra")
	} 
	k = -85
	println("Se agrega el valor ${k} al final y se obtiene la lista")
	lista1.agregarAlFinal(k)
	println(lista1.toString())
	k = (-1000..1000).random()
	println("Se busca la clave aleatoria ${k} en la lista y se obtiene que")
	busqueda = lista1.buscar(k)
	if (busqueda == null) {
		println("No se encuentra")
	} else {
		println("Sí se encuentra")
	}
	var Nodo1 = Nodo(-85)
	// Caso dos nodos con la misma clave, uno pertenece a la lista y el otro no
	println("Se crea un nuevo nodo de clave ${Nodo1.value}")
	println("Luego, dicho nodo se elimina de la lista, obteniendo la nueva lista")
	lista1.eliminar(Nodo1)
	println(lista1.toString())
	println("Notemos que el valor ${Nodo1.value} sí pertenecía a la lista, pero el nodo recién creado de clave ${Nodo1.value} no")
	println("Por eso no hubo cambios en la lista y el valor ${Nodo1.value} no fue eliminado")
	println("Ahora, se busca el valor ${-85} en la lista y se obtiene el nodo que lo contiene")
	var Nodo2 = lista1.buscar(-85)
	println("Luego, se elimina dicho nodo y se obtiene la lista")
	lista1.eliminar(Nodo2!!)
	println(lista1.toString())
	println("Y en este caso el valor ${-85} sí fue eliminado pues se hizo referencia al nodo de clave ${-85} que sí pertenecía a la lista")
	println("Y si se intenta eliminar este mismo nodo de nuevo")
	lista1.eliminar(Nodo2)
	println(lista1.toString())
	println("Se obtiene que no hay cambios en la lista pues ya dicho nodo había sido eliminado")
	println(" ")

	// Probar la clase Cola
	
	println("Se prueba la clase Cola")
	println(" ")
	var cantidadInicialCola = 10
	var cola1 = Cola(ListaCircular())
	println("Se crea una cola de ${cantidadInicialCola} elementos aleatorios")
	for (i in 0 until cantidadInicialCola) {
		var t = (-1000..1000).random()
		cola1.encolar(t)
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	var cantidadDesencolar = 5
	println("Se desencolan ${cantidadDesencolar} elementos")
	for (i in 0 until cantidadDesencolar) {
		cola1.desencolar()
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	var cantidadEncolar = 20
	println("Se encolan ${cantidadEncolar} elementos aleatorios")
	for (i in 0 until cantidadEncolar) {
		var t = (-1000..1000).random()
		cola1.encolar(t)
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	cantidadDesencolar = cantidadInicialCola - cantidadDesencolar + cantidadEncolar
	println("Se desencolan ${cantidadDesencolar} elementos")
	for (i in 0 until cantidadDesencolar) {
		cola1.desencolar()
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")

	var cola2 = Cola(ListaCircular())
	var cantidadInicialCola2 = 20
	println("Se crea una segunda cola de ${cantidadInicialCola2} elementos aleatorios")
	for (i in 0 until cantidadInicialCola2) {
		var t = (-10000..10000).random()
		cola2.encolar(t)
	}
	println("La cola 2 actual es")
	println(cola2.toString())
	println("El primer elemento es ${cola2.primero()}")
	println("La cola está vacía es: ${cola2.estaVacia()}")
	println("Se desencolan los primeros 10 elementos de la cola 2 y se encolan en la cola 1")
	for (i in 0 until 10) {
		var primero2 = cola2.primero()
		cola2.desencolar()
		cola1.encolar(primero2)
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	println("La cola 2 actual es")
	println(cola2.toString())
	println("El primer elemento es ${cola2.primero()}")
	println("La cola está vacía es: ${cola2.estaVacia()}")
	println(" ")

	// Probar la clase Pila
	
	println("Se prueba la clase Pila")
	println(" ")

	var pila1 = Pila(ListaCircular())
	var cantidadInicialPila = 15
	println("Se crea una pila de ${cantidadInicialPila} elementos aleatorios")
	for (i in 0 until cantidadInicialPila) {
		var t = (-1000..1000).random()
		pila1.empilar(t)
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	var cantidadDesempilar = 7
	println("Se desempilan ${cantidadDesempilar} elementos de la pila 1")
	for (i in 0 until cantidadDesempilar) {
		pila1.desempilar()
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	var cantidadEmpilar = 8
	println("Se empilan ${cantidadEmpilar} elementos aleatorios a la pila 1")
	for (i in 0 until cantidadEmpilar){
		var t = (-1000..1000).random()
		pila1.empilar(t)
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	cantidadDesempilar = cantidadInicialPila - cantidadDesempilar + cantidadEmpilar
	println("Se desempilan ${cantidadDesempilar} elementos de la pila 1")
	for (i in 0 until cantidadDesempilar) {
		pila1.desempilar()
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	var pila2 = Pila(ListaCircular())
	var cantidadInicialPila2 = 32
	println("Se crea una segunda pila con ${cantidadInicialPila2} elementos aleatorios")
	for (i in 0 until cantidadInicialPila2) {
		var t = (-1000..1000).random()
		pila2.empilar(t)
	}
	println("La pila 2 actual es")
	println(pila2.toString())
	println("El tope de la pila 2 es: ${pila2.tope()}")
	println("La pila 2 está vacía es: ${pila2.estaVacia()}")
	println("Se desempilan los primeros 22 elementos de la pila 2 y se empilan en la pila 1")
	for (i in 0 until 22) {
		var tope2 = pila2.tope()
		pila2.desempilar()
		pila1.empilar(tope2)
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	println("La pila 2 actual es")
	println(pila2.toString())
	println("El tope de la pila 2 es: ${pila2.tope()}")
	println("La pila 2 está vacía es: ${pila2.estaVacia()}")
}

class Nodo (var value: Int? = null) {
    var prev: Nodo? = null
    var next: Nodo? = null
}

class ListaCircular(var lista: Nodo? = Nodo()) {
	var numeroNodos = 0
    fun agregarAlFrente(k: Int) {
        // Creación del nodo
        val key = Nodo(k)
        // Creación de Centinela para su modificación
        var centinela = lista
        // Se verifica si es una lista vacía
        if (centinela?.next == null) {
            centinela?.next = key
            centinela?.prev = key 
            
            key.prev = centinela
            key.next = centinela
        } else {
            // Caso en donde no está vacía
            // Creación del primer elemento para modificar su apuntador
        	var primero = centinela.next
            primero?.prev = key
            // Se procede a indicar los apuntadores del nodo que se agregará al frente
            key.next = primero
            key.prev = centinela
            // Se arregla el apuntador del centinela
            centinela.next = key
        }
        numeroNodos++
    }
    fun agregarAlFinal(k: Int) {
        // Creación del nodo
        val key = Nodo(k)
        // Creación de Centinela para su modificación
        var centinela = lista
        // Se verifica si es una lista vacía, en ese caso vamos a la función agregarAlInicio, pues es lo mismo
        if (centinela?.next == null) {
            agregarAlFrente(k)
        } else {
            // Caso en donde no está vacía
            // Creación del último elemento para modificar su apuntador
            var last = centinela.prev
            last?.next = key 
            // Se procede a indicar los apuntadores del nodo que se agregará al frente
            key.prev = last
            key.next = centinela
            // Se arregla el apuntador del centinela
            centinela.prev = key
        }
        numeroNodos++
    }
    fun buscar(value: Int): Nodo? {
        // Usamos el nodo de la lista
        var x = lista?.next
        // Buscamos el nodo que sea igual al número ingresado, es decir, que su key sea igual al valor
        while (x?.value != value && x?.value != null) {
            x = x.next
        }
        // Verificamos si el valor del nodo encontrado es igual al valor a buscado, en el caso que es cierto nos devuelve el nodo
        if (x?.value == value) {
        	return x
        }
        // En caso contrario nos devuelve null
        return null
    }
    fun eliminar(key: Nodo?) {
        // El nodo ingresado debe pertenecer a la lista, de lo contrario no habrá cambios en la lista circular
        // Se verifica que el nodo no tenga clave vacía ni apuntadores vacíos
        if (key?.value != null && key.prev != null && key.next != null) {
            // Creación de variables que son nodos de los apuntadores del nodo ingresado
            var ladoDerecho: Nodo? = key.next
            var ladoIzquierdo: Nodo? = key.prev
            // Corregimos los apuntadores de los nodos para que apunten entre sí
            // De esta forma se elimina el nodo ingresado
            ladoDerecho?.prev = ladoIzquierdo
            ladoIzquierdo?.next = ladoDerecho
            numeroNodos--
   	    }
    }
    fun printValues() {
        // Impresión de los valores de cada nodo, empezando por el primer nodo y se detiene en el centinela
        var e = lista?.next
        while (e?.value != null) {
            println("${e.value}")
            e = e.next
        }
    }

    override fun toString(): String {
    	// Convertir la lista en un String, empezando por el primer nodo y se detiene en el centinela
		var listaCircular = ""
		var x = lista?.next
		while(x?.value != null) {
			listaCircular = listaCircular+" ${x.value} "
			x = x.next 
		}
		return listaCircular 
	}
}

class Cola(contenido: ListaCircular) {
	var secuencia: ListaCircular = contenido
		set(value) {
			field = value
		}
	
	fun encolar(e: Int) {
		secuencia.agregarAlFinal(e)
	}

	fun desencolar() {
		var x = secuencia.lista?.next
		secuencia.eliminar(x)
	}

	fun primero(): Int {
		var x = secuencia.lista?.next
		var primero = x?.value
		if (primero == null) {
			println("La cola está vacía. Por defecto, se devuelve 0 como primer elemento")
			return 0
		} else {
			return primero
		}
	}

	fun estaVacia(): Boolean{
		var numeroElementos = secuencia.numeroNodos
		if (numeroElementos <= 0) {
			return true
		} else {
			return false
		}
	}

	override fun toString(): String{
		var rep = secuencia.toString()
		return rep
	}
}

class Pila(contenido: ListaCircular) {
	var secuencia: ListaCircular = contenido
		set(value) {
			field = value
		}
	
	fun empilar(e: Int) {
		secuencia.agregarAlFinal(e)
	}

	fun desempilar() {
		var x = secuencia.lista?.prev
		secuencia.eliminar(x)
	}

	fun tope(): Int {
		var x = secuencia.lista?.prev
		var tope = x?.value
		if (tope == null) {
			println("La pila está vacía. Por defecto, se devuelve 0 como tope")
			return 0
		} else {
			return tope
		}
	}

	fun estaVacia(): Boolean{
		var numeroElementos = secuencia.numeroNodos
		if (numeroElementos <= 0) {
			return true
		} else {
			return false
		}
	}

	override fun toString(): String{
		var rep = secuencia.toString()
		return rep
	}
}
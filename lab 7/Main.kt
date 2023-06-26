fun main() {

	// Probar la clase ListaCircular
	
	println("Se prueba la clase ListaCircular")
	println(" ")
	var lista1 = ListaCircular()
	println("Se crea una lista circular y se le agregan, uno a uno, 5 enteros aleatorios")
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
		println("Luego, se elimina el nodo que tiene ${k} como valor y se obtiene la nueva lista")
		lista1.eliminar(busqueda)
		println(lista1.toString())
	}
	println("De nuevo, se busca la valor ${k} en la lista y se obtiene que")
	busqueda = lista1.buscar(k)
	if (busqueda == null) {
		println("Ya no se encuentra")
	} 
	k = -85
	println("Se agrega el valor ${k} al final y se obtiene la lista")
	lista1.agregarAlFinal(k)
	println(lista1.toString())
	var Nodo1 = Nodo(-85)
	// Caso dos nodos con el mismo valor, uno pertenece a la lista y el otro no
	println("Se crea un nuevo nodo de valor ${Nodo1.value}")
	println("Luego, dicho nodo se elimina de la lista y se obtiene")
	lista1.eliminar(Nodo1)
	println(lista1.toString())
	println("Notemos que el valor ${Nodo1.value} sí pertenecía a la lista, pero el nodo recién creado de valor ${Nodo1.value} no")
	println("Por eso no hubo cambios en la lista y el valor ${Nodo1.value} no fue eliminado")
	println("Ahora, se busca el valor ${-85} en la lista y se obtiene el nodo que lo contiene")
	var Nodo2 = lista1.buscar(-85)
	println("Luego, se elimina dicho nodo y se obtiene la lista")
	lista1.eliminar(Nodo2)
	println(lista1.toString())
	println("Y en este caso el valor ${-85} sí fue eliminado pues se hizo referencia al nodo de valor ${-85} que sí pertenecía a la lista")
	println("Y si se intenta eliminar este mismo nodo de nuevo")
	lista1.eliminar(Nodo2)
	println(lista1.toString())
	println("Se obtiene que no hay cambios en la lista pues ya dicho nodo había sido eliminado")
	println(" ")

	// Probar la clase Cola
	
	println("Se prueba la clase Cola")
	println(" ")
	var cantidadInicialCola = 5
	var cola1 = Cola()
	println("Se crea una cola y se le encolan, uno a uno, ${cantidadInicialCola} elementos aleatorios")
	for (i in 0 until cantidadInicialCola) {
		var t = (-1000..1000).random()
		cola1.encolar(t)
		println(cola1.toString())
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	var cantidadDesencolar = 3
	println("Se desencolan ${cantidadDesencolar} elementos")
	for (i in 0 until cantidadDesencolar) {
		cola1.desencolar()
		println(cola1.toString())
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	var cantidadEncolar = 10
	println("Se encolan, uno a uno, ${cantidadEncolar} elementos aleatorios")
	for (i in 0 until cantidadEncolar) {
		var t = (-1000..1000).random()
		cola1.encolar(t)
		println(cola1.toString())
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")
	cantidadDesencolar = cantidadInicialCola - cantidadDesencolar + cantidadEncolar
	println("Se desencolan ${cantidadDesencolar} elementos")
	for (i in 0 until cantidadDesencolar) {
		cola1.desencolar()
		println(cola1.toString())
	}
	println("La cola 1 actual es")
	println(cola1.toString())
	println("El primer elemento es ${cola1.primero()}")
	println("La cola está vacía es: ${cola1.estaVacia()}")

	var cola2 = Cola()
	var cantidadInicialCola2 = 9
	println("Se crea una segunda cola y se le encolan, uno a uno, ${cantidadInicialCola2} elementos aleatorios")
	for (i in 0 until cantidadInicialCola2) {
		var t = (-10000..10000).random()
		cola2.encolar(t)
		println(cola2.toString())
	}
	println("La cola 2 actual es")
	println(cola2.toString())
	println("El primer elemento es ${cola2.primero()}")
	println("La cola está vacía es: ${cola2.estaVacia()}")
	println("Se desencolan los primeros 5 elementos de la cola 2 y se encolan en la cola 1")
	for (i in 0 until 5) {
		var primero2 = cola2.primero()
		cola2.desencolar()
		println("Cola 2: " +cola2.toString())
		cola1.encolar(primero2)
		println("Cola 1: " +cola1.toString())
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

	var pila1 = Pila()
	var cantidadInicialPila = 9
	println("Se crea una pila y se le empilan, uno a uno, ${cantidadInicialPila} elementos aleatorios")
	for (i in 0 until cantidadInicialPila) {
		var t = (-1000..1000).random()
		pila1.empilar(t)
		println(pila1.toString())
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	var cantidadDesempilar = 4
	println("Se desempilan, uno a uno, ${cantidadDesempilar} elementos de la pila 1")
	for (i in 0 until cantidadDesempilar) {
		pila1.desempilar()
		println(pila1.toString())
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
		println(pila1.toString())
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	cantidadDesempilar = cantidadInicialPila - cantidadDesempilar + cantidadEmpilar
	println("Se desempilan ${cantidadDesempilar} elementos de la pila 1")
	for (i in 0 until cantidadDesempilar) {
		pila1.desempilar()
		println(pila1.toString())
	}
	println("La pila 1 actual es")
	println(pila1.toString())
	println("El tope de la pila 1 es: ${pila1.tope()}")
	println("La pila 1 está vacía es: ${pila1.estaVacia()}")
	var pila2 = Pila()
	var cantidadInicialPila2 = 7
	println("Se crea una segunda pila y se le empilan, uno a uno, ${cantidadInicialPila2} elementos aleatorios")
	for (i in 0 until cantidadInicialPila2) {
		var t = (-1000..1000).random()
		pila2.empilar(t)
		println(pila2.toString())
	}
	println("La pila 2 actual es")
	println(pila2.toString())
	println("El tope de la pila 2 es: ${pila2.tope()}")
	println("La pila 2 está vacía es: ${pila2.estaVacia()}")
	println("Se desempilan los primeros 4 elementos de la pila 2 y se empilan en la pila 1")
	for (i in 0 until 4) {
		var tope2 = pila2.tope()
		pila2.desempilar()
		println("Pila 2: " +pila2.toString())
		pila1.empilar(tope2)
		println("Pila 1: " +pila1.toString())
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

class Cola {

    var secuencia: ListaCircular = ListaCircular()

    fun encolar(x: Int) {
        secuencia.agregarAlFinal(x)
    }

    fun desencolar() {
        var primerNodo = secuencia.centi?.next
        if (primerNodo?.value != null) {
            secuencia.eliminar(primerNodo)
        }
    }

    fun primero(): Int {
        var primerNodo = secuencia.centi?.next
        var valor = primerNodo?.value
        if (valor != null) {
            return valor
        }
        println("La cola está vacía. Por defecto se retorna 0 como primer elemento")
        return 0
    }

    fun estaVacia(): Boolean {
        var primerNodo = secuencia.centi?.next
        if (primerNodo?.value == null) {
            return true
        }
        return false
    }

    override fun toString(): String{
        var lista = secuencia.toString()
        return lista
    }
}

class Pila {

    var secuencia: ListaCircular = ListaCircular()

    fun empilar(x: Int) {
        secuencia.agregarAlFinal(x)
    }

    fun desempilar() {
        var ultimoNodo = secuencia.centi?.prev
        if (ultimoNodo?.value != null) {
            secuencia.eliminar(ultimoNodo)
        }
    }

    fun tope(): Int {
        var ultimoNodo = secuencia.centi?.prev
        var valor = ultimoNodo?.value
        if (valor != null) {
            return valor
        }
        println("La pila está vacía. Por defecto se retorna 0 como tope")
        return 0
    }

    fun estaVacia(): Boolean {
        var primerNodo = secuencia.centi?.next
        if (primerNodo?.value == null) {
            return true
        }
        return false
    }
    
    override fun toString(): String{
        var lista = secuencia.toString()
        return lista
    }
}
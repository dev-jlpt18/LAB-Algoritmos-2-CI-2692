fun main() {

	// Probar la clase ListaCircular
	
	println("Se prueba la clase ListaCircular")
	println(" ")
	var lista1 = ListaCircular()
	println("Se crea una lista circular agregando, uno a uno, 5 nodos de clave aleatoria")
	var k = (-1000..1000).random()
	var Nodo1 = Nodo(k)
	println("El nodo 1 de clave ${Nodo1} se agrega al frente y se obtiene la lista")
	lista1.agregarAlFrente(Nodo1)
	println(lista1.toString())
	k = (-1000..1000).random()
	var Nodo2 = Nodo(k)
	println("El nodo 2 de clave ${Nodo2} se agrega al frente y se obtiene la lista")
	lista1.agregarAlFrente(Nodo2)
	println(lista1.toString())
	k = (-1000..1000).random()
	var Nodo3 = Nodo(k)
	println("El nodo 3 de clave ${Nodo3} se agrega al final y se obtiene la lista")
	lista1.agregarAlFinal(Nodo3)
	println(lista1.toString())
	k = (-1000..1000).random()
	var Nodo4 = Nodo(k)	
	println("El nodo 4 de clave ${Nodo4} se agrega al final y se obtiene la lista")
	lista1.agregarAlFinal(Nodo4)
	println(lista1.toString())
	k = (-1000..1000).random()
	var Nodo5 = Nodo(k)
	println("El nodo 5 de clave ${Nodo5} se agrega al frente y se obtiene la lista")
	lista1.agregarAlFrente(Nodo5)
	println(lista1.toString())

	println("Se elimina el nodo 3 de clave ${Nodo3} y se obtiene la lista")
	lista1.eliminar(Nodo3)
	println(lista1.toString())
	var h = ((Nodo5.key)!!).toInt()
	println("Se busca la clave ${h} y se obtiene que")
	var busqueda = lista1.buscar(h)
	if (busqueda == null) {
		println("No se encuentra")
	} else {
		println("Si se encuentra. Luego, se elimina y se obtiene la nueva lista")
		lista1.eliminar(busqueda)
		println(lista1.toString())
	}
	k = (-1000..1000).random()
	var Nodo6 = Nodo(k)
	println("Se agrega el nodo 6 de clave ${Nodo6} al final y se obtiene la lista")
	lista1.agregarAlFinal(Nodo6)
	println(lista1.toString())
	println("De nuevo, se busca la clave ${h} en la lista y se obtiene que")
	busqueda = lista1.buscar(h)
	if (busqueda == null) {
		println("No se encuentra")
	} else {
		println("Sí se encuentra. Luego, se elimina y se obtiene la nueva lista")
		lista1.eliminar(busqueda)
		println(lista1.toString())
	}
	k = (-1000..1000).random()
	println("Se busca la clave ${k} en la lista y se obtiene que")
	busqueda = lista1.buscar(k)
	if (busqueda == null) {
		println("No se encuentra")
	} else {
		println("Sí se encuentra. Luego, se elimina y se obtiene la nueva lista")
		lista1.eliminar(busqueda)
		println(lista1.toString())
	}
	var Nodo7 = Nodo(Nodo6.key)
	// Caso dos nodos con la misma clave, uno pertenece a la lista y el otro no
	println("Se crea un nuevo nodo 7 de clave ${Nodo7}, es decir, la misma clave del nodo 6, el cual está en la lista")
	println("Luego, el nodo 7 se elimina de la lista, obteniendo la nueva lista")
	// Se elimina el nodo que no pertenece a la lista y no hay cambios
	lista1.eliminar(Nodo7)
	println(lista1.toString())
	println("Notemos que el valor ${Nodo7} sí pertenecía a la lista, pero el nodo 7 de clave ${Nodo7} no")
	println("Por eso no hubo cambios en la lista y el valor ${Nodo7} no fue eliminado")
	println("En cambio, si se elimina el nodo 6 de clave ${Nodo6} se obtiene la lista")
	lista1.eliminar(Nodo6)
	println(lista1.toString())
	// Caso eliminar un mismo elemento dos veces
	println("Y si de nuevo se elimina el nodo 6 de clave ${Nodo6} de la lista, se obtiene la nueva lista")
	lista1.eliminar(Nodo6)
	println(lista1.toString())
	println("No hay cambios pues el nodo 6 ya no formaba parte de la lista")
	println(" ")

	/* Falta arreglar la función eliminar si el elemento no está en la lista
	 * En particular, el siguiente caso genera un error: Se tiene un NodoX que pertenece a la lista 2 y
	 * no pertenece a la lista 1. Si se intenta eliminar el NodoX de la lista 1, se alteran ambas listas
	 */

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
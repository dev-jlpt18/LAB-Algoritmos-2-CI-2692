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
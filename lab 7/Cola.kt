class Cola {
    var secuencia: ListaCircular = ListaCircular()
    fun encolar(x: Int) {
        secuencia.agregarAlFrente(x)
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
        valor = 0
        return valor
    }
    fun estaVacia(): Boolean {
        var primerNodo = secuencia.centi?.next
        if (primerNodo?.value == null) {
            return true
        }
        return false
    }
    override fun toString(): String{
        var lista = secuencia.printValues()
        return lista
    }
}
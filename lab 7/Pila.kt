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
        var lista = secuencia.printValues()
        return lista
    }
}
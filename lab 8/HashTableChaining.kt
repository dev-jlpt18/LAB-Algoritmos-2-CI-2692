class HashTableChaining {
    var n = 0
    var m = 7
    var claves: Array<CircularList> = Array(7){CircularList()}
    var newclaves: Array<CircularList> = Array(m){CircularList()}
    var factorCarga = n.toDouble()/m.toDouble()
        set(value) {
            field = if (factorCarga >= 0.7) {
                incr()
                newclaves = Array(m){CircularList()}
                for (i in 0 until claves.size) {
                    newclaves[i] = claves[i] 
                }
                claves = newclaves
                n.toDouble()/m.toDouble()
            } else {
                value
            }
        }
   
    fun incr(){
        m = ((n+16)*3/2).toInt()
    }

    fun hash(k: Int, m: Int): Int {
        var h = k%m
        return h
    }

    fun agregar(k: Int, valor: String) {
        var x = hash(k, m)
        var casilla = claves[x]
        casilla.agregar(k,valor)
        n++
        factorCarga = n.toDouble()/m.toDouble()
    }
    fun eliminar(k: Int) {
        var x = hash(k, m)
        var posicion = claves[x]
        var slot = posicion.buscar(k)
        posicion.eliminar(slot)
        n--
        factorCarga = n.toDouble()/m.toDouble()
    }
    fun buscar(clave: Int): String? {
        var x = hash(clave, m)
        var posicion = claves[x]
        var y = posicion.buscar(clave)
        return y?.valor
    }
    fun existe(clave: Int): Boolean {
        var slot = buscar(clave)
        if (slot != null) {
            return true
        } else {
            return false
        }
    }
    override fun toString(): String {
        var valores = ""
        for (i in 0 until claves.size) {
            var lista = claves[i]
            valores = valores + lista.printValues()
        }
        return valores 
    }
    fun numElementos(): Int {
        return n
    }
}
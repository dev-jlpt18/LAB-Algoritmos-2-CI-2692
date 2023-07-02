import kotlin.math.roundToInt
class HashTableChaining {
    var n = 0
    var m = 7
    var m2 = 0
    var key: Array<CircularList> = Array(7){CircularList()}
    var newclaves: Array<CircularList> = Array(m){CircularList()}
    var factorCarga = n.toDouble()/m.toDouble()
        set(value) {
            field = if (factorCarga >= 0.7) {
                m2 = incr()
                m2 = m2-m
                newclaves = Array(m2){CircularList()}
                key = key+newclaves
                m = key.size
                n.toDouble()/m.toDouble()
            } else {
                value
            }
        }
   
    fun incr(): Int{
        return ((m+16)*3/2).toInt()
    }

    fun hash(k: Int, m: Int): Int {
        var h = k%m
        return h
    }

    fun agregar(k: Int, valor: String) {
        var h = hash(k, m)
        var casilla = key[h]
        casilla.agregar(k,valor)
        n++
        factorCarga = n.toDouble()/m.toDouble()
    }
    fun eliminar(k: Int) {
        var h = hash(k, m)
        var posicion = key[h]
        var slot = posicion.buscar(k)
        posicion.eliminar(slot)
        n--
        factorCarga = n.toDouble()/m.toDouble()
    }
    fun buscar(clave: Int): String? {
        var h = hash(clave, m)
        var posicion = key[h]
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
        for (i in 0 until key.size) {
            var lista = key[i]
            valores = valores + lista.printValues()
        }
        return valores 
    }
    fun numElementos(): Int {
        return n
    }
}
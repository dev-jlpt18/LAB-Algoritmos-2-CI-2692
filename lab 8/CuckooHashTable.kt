class CuckooHashTable {
    var n = 0
    var m = 7
    var m2 = 0
    var T1: Array<CuckooHashTableEntry?> = Array(7){CuckooHashTableEntry()}
    var T2: Array<CuckooHashTableEntry?> = Array(7){CuckooHashTableEntry()}
    var newclave: Array<CuckooHashTableEntry?> = Array(m){CuckooHashTableEntry()}
    var factorCarga = n.toDouble()/m.toDouble()
        set(value) {
            field = if (factorCarga >= 0.7) {
                rehashing()
                n.toDouble()/m.toDouble()
            } else {
                value
            }
        }
    fun rehashing() {
        m2 = incr()
        m2 = m2-m
        newclave = Array(m2){CuckooHashTableEntry()}
        T1 = T1+newclave
        T2 = T2+newclave
        m = T1.size
    }
    fun incr(): Int{
        return ((m+16)*1.5).toInt()
    }
    fun hash1(k: Int): Int {
        var h = k%m
        return h
    }
    fun hash2(k: Int): Int {
        val A = 0.6180339887
        var h = (m*((k*A)%1)).toInt()
        return h
    }
    
    fun buscar(x: Int): String?{
        if (T1[hash1(x)]?.clave == x) {
            var k = T1[hash1(x)]?.valor
            return k
        } else if (T2[hash2(x)]?.clave == x) {
            var k = T2[hash2(x)]?.valor
            return k
        }
        return null
    }
    fun eliminar(x: Int){
        if (T1[hash1(x)]?.clave == x) {
            T1[hash1(x)]?.clave = null
            T1[hash1(x)]?.valor = null
            n--
            factorCarga = n.toDouble()/m.toDouble()
            return
        } else if (T2[hash2(x)]?.clave == x) {
            T2[hash2(x)]?.clave = null
            T2[hash2(x)]?.valor = null
            n--
            factorCarga = n.toDouble()/m.toDouble()
            return
        }
    }
    fun agregar(key: Int, valor: String){
        var x: Int? = key
        var y: String? = valor
        if (buscar(x!!) != null) {
            return 
        }
        var elemento: CuckooHashTableEntry? = CuckooHashTableEntry(x,y)
        for (i in 0 until m/3) {
            var slot = T1[hash1(x!!)]
            T1[hash1(x)] = elemento
            elemento = slot
            if (elemento?.clave == null) {
                n++
                factorCarga = n.toDouble()/m.toDouble()
                return
            }
            slot = T2[hash2(x)]
            T2[hash2(x)] = elemento
            elemento = slot
            if (elemento?.clave == null) {
                n++
                factorCarga = n.toDouble()/m.toDouble()
                return
            }
        }
        rehashing()
        agregar(x,y!!)
    }
    
}
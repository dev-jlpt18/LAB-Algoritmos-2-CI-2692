class CuckooHashTable {
    var n = 0
    var m = 7
    var T1: Array<CuckooHashTableEntry?> = Array(7){CuckooHashTableEntry()}
    var T2: Array<CuckooHashTableEntry?> = Array(7){CuckooHashTableEntry()}
    var clave: Array<CuckooHashTableEntry?> = Array(m){CuckooHashTableEntry()}
    var clave2: Array<CuckooHashTableEntry?> = Array(m){CuckooHashTableEntry()}
    var factorCarga = n/m
    fun check() {
        if (factorCarga >= 0.7) {
            rehashing()
            for (i in 0 until T1.size) {
                clave[i] = T1[i] 
                clave2[i] = T2[i] 
            }
            T1 = clave
            T2 = clave2
        }  
    }
    
    fun rehashing(){
        m = ((n+16)*3/2).roundToInt()
    }
    fun hash1(k: int): Int {
        var clave = k%m
        return clave
    }
    fun hash2(k: Int): Int {
        val A = 0,6180339887
        var clave = m((k*A)%1).toInt()
        return clave
    }
    
    fun search(x: Int): String{
        if (T1[hash1(x)].clave == x) {
            return T1[hash1(x)].valor
        } else if (T2[hash2(x)].clave == x) {
            return T2[hash2(x)].valor
        }
        return null
    }
    fun delete(x: Int){
        if (T1[hash1(x)].clave == x) {
            T1[hash1(x)] = null
            return
        } else if (T2[hash2(x)].clave == x) {
            T2[hash2(x)].clave = null
            return
        }
    }
    fun insert(x: Int, y: String){
        if (search(x) != null) {
            return 
        }
        for (i in 0 until m) {
            swap(x, y, T1[hash1(x)])
            if (x == null) {
                n++
                return
            }
            swap(x, y, T2[hash2(x)])
            if (x == null) {
                n++
                return
            }
        }
        rehashing()
        insert(x,y)
    }
    fun swap(x: Int, y: String, slot: CuckooHashTableEntry) {
        var tmp = slot.clave
        var tmp2 = slot.valor
        slot.clave = x
        slot.valor = y
        x = tmp
        y = tmp2
    }
}
class ArbolBinario {
    val letters:Array<Char?> = arrayOf(null,'e','t','i','a','n','m','s','u','r','w','d','k','g','o','h','v','f','l','p','j','b','x','c','y','z','q')
    val Nodos:Array<Nodo?> = Array(letters.size){Nodo()}

    init {
        var hijoD: Int
        var hijoIz: Int
        for(i in 0 until Nodos.size) {
            if(i >= 15) {
                var padre = Nodos[i]
                padre?.letra = letters[i]
                padre?.next = null
                padre?.prev = null
            } else {
                hijoD = (i+1)*2
                hijoIz = (2*i)+1

                if (i == 8 || i == 9) {
                    when(i) {
                        8 -> hijoIz = 17
                        9 -> hijoIz = 18
                    }
                    Nodos[hijoIz] = Nodo(letters[hijoIz])
                        
                    var padre = Nodos[i]
                    padre?.letra = letters[i]
                    padre?.next = null
                    padre?.prev = Nodos[hijoIz]
                } else if (i == 10 || i == 11 || i == 12 || i == 13) {
                    hijoD = 2*i
                    hijoIz = (2*i)-1

                    Nodos[hijoIz] = Nodo(letters[hijoIz])
                    Nodos[hijoD] = Nodo(letters[hijoD])

                    var padre = Nodos[i]
                    padre?.letra = letters[i]
                    padre?.next = Nodos[hijoD]
                    padre?.prev = Nodos[hijoIz]
                } else if (i == 14) {
                    var padre = Nodos[i]
                    padre?.letra = letters[i]
                    padre?.next = null
                    padre?.prev = null
                } else {
                    Nodos[hijoIz] = Nodo(letters[hijoIz])
                    Nodos[hijoD] = Nodo(letters[hijoD])

                    var padre = Nodos[i]
                    
                    padre?.letra = letters[i]
                    padre?.next = Nodos[hijoD]
                    padre?.prev = Nodos[hijoIz]
                }
            }
        }  
    }
}

class Nodo(var letra: Char? = null){
    var prev: Nodo? = null
    var next: Nodo? = null
}
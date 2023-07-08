class CodigoMorse {

    // Constructor CrearCodigoMorse() 
    val arbolCodigoMorse = ArbolBinarioMorse()

    fun decodificarLetra(secuencia: String): String? {
        var x: Nodo? = arbolCodigoMorse.root
        var n = secuencia.length
        var i = 0
        while(x?.key != null && i < n) {
            var rama = secuencia[i].toString()
            if (rama == ".") {
                x = x.left
            } else if (rama == "-") {
                x = x.right
            } else {
                return null
            }
            i++
        }
        return x?.key
    }

    fun decodificarMensaje(frase: String): String? {
        var fraseDecodificada = ""
        var n = frase.length
        var i = 0
        while (i < n) {
            var letra = ""
            var caracter = frase[i].toString()
            while ((caracter == "." || caracter == "-") && i < n) {
                letra = letra+caracter
                i++ 
                if (i < n) {
                   caracter = frase[i].toString() 
                }
            }
            var x = decodificarLetra(letra)
            if (x == null) {
                return null
            }
            fraseDecodificada = fraseDecodificada + "${x}"
            if (i < n) {
                if (caracter == " ") {
                    i++
                } else if (caracter == "/") {
                    fraseDecodificada = fraseDecodificada + " "
                    i++
                } else {
                    return null
                } 
            }
        }
        return fraseDecodificada
    }
}
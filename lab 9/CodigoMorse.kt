class CodigoMorse {
    val abecedario = ArbolBinario()
    fun decodificarLetra(cadena: String): String?{
        var palabra = ""
        val letra = abecedario.Nodos
        var caracter: Nodo? = letra[0]
        for (i in cadena) {
            if (i == '.') {
                caracter = caracter?.prev
            } else if (i == '-'){
                caracter = caracter?.next
            }
        }
        if (caracter?.letra == null) {
            return null
        } else {
            palabra = palabra + caracter.letra
            return palabra
        }
    }
    fun decodificarMensaje(mensaje:String): String?{
        var oracion = ""
        var morse = ""
        var letra: String?
        for(i in mensaje) {
            if (i == ' ') {
                letra = decodificarLetra(morse)
                if (letra == null) {
                    return null
                } else {
                    oracion = oracion + letra
                    morse = ""
                }
            } else if (i == '/') {
                letra = decodificarLetra(morse)
                if (letra == null) {
                    return null
                } else {
                	oracion = oracion + letra + " "
                	morse = ""
                }
            }else {
                morse = morse + i
            }
        }
        return oracion
    }
}
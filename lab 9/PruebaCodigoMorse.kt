fun main (args: Array<String>) {
	var frase = ""
	for (i in args) {
		frase = frase + i + " "
	}
    println("$frase")
	var pruebaCodigoMorse = CodigoMorse()
	var fraseDecodificada = pruebaCodigoMorse.decodificarMensaje(frase)
	if (fraseDecodificada == null) {
		println("Error, código morse no válido")
	} else {
		println(fraseDecodificada)
	}
}
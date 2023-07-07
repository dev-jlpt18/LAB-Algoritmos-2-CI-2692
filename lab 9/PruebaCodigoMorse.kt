fun main (args: Array<String>) {
	var frase = ""
	for (i in 0 until args.size-1) {
		frase = frase + args[i] + " "
	}
	frase = frase + args[args.size-1]
	var pruebaCodigoMorse = CodigoMorse()
	var fraseDecodificada = pruebaCodigoMorse.decodificarMensaje(frase)
	if (fraseDecodificada == null) {
		println("Error, código morse no válido")
	} else {
		println(fraseDecodificada)
	}
}
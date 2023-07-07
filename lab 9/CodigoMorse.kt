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

class ArbolBinarioMorse {
    var root = Nodo("")
    var letraA = Nodo("a")
    var letraB = Nodo("b")
    var letraC = Nodo("c")
    var letraD = Nodo("d")
    var letraE = Nodo("e")
    var letraF = Nodo("f")
    var letraG = Nodo("g")
    var letraH = Nodo("h")
    var letraI = Nodo("i")
    var letraJ = Nodo("j")
    var letraK = Nodo("k")
    var letraL = Nodo("l")
    var letraM = Nodo("m")
    var letraN = Nodo("n")
    var letraO = Nodo("o")
    var letraP = Nodo("p")
    var letraQ = Nodo("q")
    var letraR = Nodo("r")
    var letraS = Nodo("s")
    var letraT = Nodo("t")
    var letraU = Nodo("u")
    var letraV = Nodo("v")
    var letraW = Nodo("w")
    var letraX = Nodo("x")
    var letraY = Nodo("y")
    var letraZ = Nodo("z")

    init {
        root.left = letraE
        root.right = letraT
        letraE.left = letraI
        letraE.right = letraA
        letraT.left = letraN
        letraT.right = letraM
        letraI.left = letraS
        letraI.right = letraU
        letraA.left = letraR
        letraA.right = letraW
        letraN.left = letraD
        letraN.right = letraK
        letraM.left = letraG
        letraM.right = letraO
        letraS.left = letraH
        letraS.right = letraV
        letraU.left = letraF
        letraR.left = letraL
        letraW.left = letraP
        letraW.right = letraJ
        letraD.left = letraB
        letraD.right = letraX
        letraK.left = letraC
        letraK.right = letraY
        letraG.left = letraZ
        letraG.right = letraQ
    }
}

class Nodo (var key: String? = null) {

    var right: Nodo? = null
    var left: Nodo? = null
}
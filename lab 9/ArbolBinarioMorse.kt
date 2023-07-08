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
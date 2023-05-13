fun main(args: Array<String>) {
    var t: Int = 0
    var s: String = ""
    var n: Int = 0
    for (i in 0 until (args.size-1)) {
        when (args[i]) {
            "-t" -> t = args[i+1].toInt()
            "-n" -> n = args[i+1].toInt()
            "-s" -> s = args[i+1]
        }
    }
    var secuencia: Array<Int> = generador(s, n)
    insertionSort(secuencia)
    selectionSort(secuencia)
    shellSort(secuencia)
    bubbleSort(secuencia)
}

fun generador(id: String, tamaño: String): Array<Int> {
    var secuencia = Array<Int>(tamaño,{0})
    if (id == "random") {
        for (i in 0 until (secuencia.size-1)) {
            secuencia[i] = (0..tamaño).random()
        }
        return secuencia
    } else if (id == "sorted") {
        for (i in 0 until (secuencia.size-1) ) {
            secuencia[i] = i+1
        }
        return secuencia
    } else if (id == "inv") {
        for (i in 0 until (secuencia.size-1) ) {
            secuencia[i] = tamaño-i
        }
        return secuencia
    } else if (id == "zu") {
        for (i in 0 until (secuencia.size-1) ) {
            secuencia[i] = (0..1).random()
        }
        return secuencia
    } else if (id == "media") {
        for (i in 0 until (secuencia.size-1) ) {
            if (i < tamaño/2) {
                secuencia[i] = i+1 
            } else {
                secuencia[i] = tamaño-i
            }
        }
        return secuencia
    } else {
        return secuencia
    }
}
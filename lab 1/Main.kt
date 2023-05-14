fun main(args: Array<String>) {
    var t: Int = 0
    var s: String = ""
    var n: Int = 0
    for (i in 0 until args.size) {
        when (args[i]) {
            "-t" -> t = args[i+1].toInt()
            "-n" -> n = args[i+1].toInt()
            "-s" -> s = args[i+1]
        }
    }
    var secuencia: Array<Int> = generador(s, n)
    println("Tenemos que: ${t}, ${s} y ${n}")
    println("Secuencia generada:" + secuencia.contentToString())
    insertionSort(secuencia)
    println("Insertion Sort:" + secuencia.contentToString())
    
    

}

fun generador(id: String, tamaño: Int): Array<Int> {
    var secuencia = Array<Int>(tamaño,{0})
    if (id == "random") {
        for (i in 0 until tamaño) {
            secuencia[i] = (0..tamaño).random()
        }
        return secuencia
    } else if (id == "sorted") {
        for (i in 0 until tamaño) {
            secuencia[i] = i+1
        }
        return secuencia
    } else if (id == "inv") {
        for (i in 0 until tamaño) {
            secuencia[i] = tamaño-i
        }
        return secuencia
    } else if (id == "zu") {
        for (i in 0 until tamaño) {
            secuencia[i] = (0..1).random()
        }
        return secuencia
    } else if (id == "media") {
        for (i in 0 until tamaño) {
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

fun orden(A: Array<Int>) {
    for (i in 0 until (A.size-2)) {
        if (A[i] > A[i+1]) {
            println("La secuencia no esta ordenada")
            break
        }
    }
}
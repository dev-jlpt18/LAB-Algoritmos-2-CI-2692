fun main() {
   val (nombre, edad, pais) = obtenerInformacionPersonal()
    println("Nombre: $nombre")
    println("Edad: $edad")
    println("País: $pais")
 
    var A = arrayOf(1,2,3,4)
    var b = swap(A,1, 2)
    println(A.contentToString())
    println(b)
    // Colocamos el Pair dentro de la variable 'miPair' 
    var miPair = Pair("Carlos", 34)
    println(miPair.first)
    println(miPair.second) 
}
fun obtenerInformacionPersonal(): Triple<String, Int, String> {
    return Triple("Carlos", 34, "Perú")
}
fun swap (A: Array<Int>,x: Int, y: Int): Int {
    var temp: Int = A[x]
    A[x] = A[y]
    A[y] = temp
    return 1
}

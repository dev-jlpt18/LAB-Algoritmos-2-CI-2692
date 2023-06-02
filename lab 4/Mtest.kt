fun main() {
    var o = -1
    var A = arrayOf(1,9,78,5,456,135,465,23,46,87,4354,13,46,79,46,4,9463)
    var B = arrayOf(1,9,78,5,456,135,465,23,46,87,4354,13,46,79,46,4,9463,465465,57987,16546,1374,6546,2,46451,654,2165,41,354,61,354,2,654,3,16,54,21,65,745,7496,4,2)
    var C = arrayOf(1,56,48,13,79,54,12,79,1,56,4)
    var D = arrayOf(5,4,8,6,3,2,1)
    while(o++ < 10) {
        println(o)
    }
    quicksortDualPivot(D)
    println(D.contentToString())
    if (estaEnOrdenAscendente(D) == false) {
        println("Error")
    }else {
        println("Ordenado de manera satisfactoria")
    }
}
fun estaEnOrdenAscendente(A: Array<Int>): Boolean {
    for (i in 0 until (A.size-1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}
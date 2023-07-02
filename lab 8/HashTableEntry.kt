class HashTableEntry(var clave: Int? = null, var valor: String? = null) {
    var prev: HashTableEntry? = null
    var next: HashTableEntry? = null
}
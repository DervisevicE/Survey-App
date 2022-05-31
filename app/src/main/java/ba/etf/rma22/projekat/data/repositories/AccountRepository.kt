package ba.etf.rma22.projekat.data.repositories

object AccountRepository {

    var mojHash : String ="5c911594-e32d-48a5-a81b-6b7e59719429"

    fun postaviHash(hash : String) : Boolean{
        try {
            mojHash = hash
            return true
        } catch (e: Exception){
            return false
        }
    }

    fun getHash() : String{
        return mojHash
    }
}
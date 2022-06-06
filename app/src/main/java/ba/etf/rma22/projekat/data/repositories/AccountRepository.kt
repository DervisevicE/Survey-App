package ba.etf.rma22.projekat.data.repositories

object AccountRepository {

    var acHash : String ="5c911594-e32d-48a5-a81b-6b7e59719429"

    fun postaviHash(hash : String) : Boolean{
        try {
            acHash = hash
            return true
        } catch (e: Exception){
            return false
        }
    }

    fun getHash() : String{
        return acHash
    }
}
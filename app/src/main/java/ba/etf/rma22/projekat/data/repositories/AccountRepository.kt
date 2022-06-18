package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import androidx.room.Room
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AccountRepository {

    //companion object{
        var acHash : String ="5c911594-e32d-48a5-a81b-6b7e59719429"
        //private lateinit var context : Context
    //}

    var context: Context?=null

    suspend fun postaviHash(hash : String) : Boolean{
        return withContext(Dispatchers.Main) {
            acHash = hash
            try{
                var db = AppDatabase.getInstance(AnketaRepository.context!!)
                db.accountDao().obrisiAccount()
                try{
                    db.accountDao().obrisiAccount()
                    val acc = ApiAdapter.retrofit.dajStudentaSaHashom(hash)
                    db.accountDao().insertAccount(acc)
                } catch (e: Exception){
                    db.accountDao().obrisiAccount()
                    val acc = Account(0, "", hash)
                    db.accountDao().insertAccount(acc)
                }
                //ovdje brisi ostalo
                return@withContext true
            } catch (e: Exception){
                return@withContext false
            }
        }
    }
    fun getHash() : String{
        return acHash
    }


    /*suspend fun getAccount() : Account {
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(AnketaRepository.context!!)
            val acc = db.accountDao().getAccount()
            return@withContext acc
        }
    }*/
}
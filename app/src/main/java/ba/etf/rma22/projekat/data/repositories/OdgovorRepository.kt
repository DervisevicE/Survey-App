package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Odgovor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object OdgovorRepository {

     suspend fun getOdgovoriAnketa(idAnketa: Int) : List<Odgovor>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getOdgovoriAnketa(AccountRepository.getHash(), idAnketa)
        }
    }

    fun postaviOdgovorAnketa(idAnketa: Int, idPitanje: Int, odgovor: Int){
       /* return withContext(Dispatchers.IO){
            return@withContext null
        }*/
    }
}
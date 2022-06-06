package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {

    suspend fun zapocniAnketu(idAnketa: Int) : AnketaTaken{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.zapocniAnketu(idAnketa, AccountRepository.getHash())

        }

    }

    suspend fun getPoceteAnkete() : List<AnketaTaken>?{
        return withContext(Dispatchers.IO){
            val poceteAnkete = ApiAdapter.retrofit.getPoceteAnkete(AccountRepository.getHash())
            if (poceteAnkete.isEmpty())
                return@withContext null
            return@withContext poceteAnkete
        }
    }
}
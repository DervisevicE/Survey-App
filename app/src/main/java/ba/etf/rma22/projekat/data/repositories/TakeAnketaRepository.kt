package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {

    var context: Context?=null

    suspend fun zapocniAnketu(idAnketa: Int) : AnketaTaken?{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if(MainActivity.connection){
                /*var anketaSaId = AnketaRepository.getByIdSaServera(idAnketa)
                anketaSaId!!.upisana=1
                db.anketaDao().insertAll(anketaSaId)
*/
                return@withContext ApiAdapter.retrofit.zapocniAnketu(idAnketa, AccountRepository.getHash())
            }
            else
                return@withContext null
        }
    }

    suspend fun getPoceteAnketeSaServisa() : List<AnketaTaken>?{
        return withContext(Dispatchers.IO){
            var response = ApiAdapter.retrofit.getPoceteAnkete()
            val responseBody = response.body()
            if (responseBody?.isEmpty() == true)
                return@withContext null
            return@withContext responseBody
        }
    }

    suspend fun getPoceteAnkete() : List<AnketaTaken>?{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if (MainActivity.connection){

                var poceteSaServisa = getPoceteAnketeSaServisa()

                if(poceteSaServisa!=null){
                    for(poceta in poceteSaServisa){
                        db.anketaTakenDao().insertAll(poceta)
                    }
                }
                return@withContext poceteSaServisa
            }else{
                var izBaze = db.anketaTakenDao().getPocete()
                return@withContext izBaze
            }
        }
    }
}
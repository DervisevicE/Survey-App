package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

object PitanjeAnketaRepository {

    suspend fun getPitanjaSaServisa(idAnkete : Int) : List<Pitanje>{
        return withContext(Dispatchers.IO){
            var response = ApiAdapter.retrofit.getPitanja(idAnkete)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getPitanja(idAnkete: Int) : List<Pitanje>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(AnketaRepository.context!!)
            if(MainActivity.connection){
                var pitanjaZaAnketuSaServisa = getPitanjaSaServisa(idAnkete)

                for(pitanje in pitanjaZaAnketuSaServisa){
                    pitanje.anketumId = idAnkete
                    db.pitanjeDao().ubaciPitanje(pitanje)
                }
                return@withContext pitanjaZaAnketuSaServisa
            }else{
                var pitanjaIzBaze = db.pitanjeDao().getPitanja(idAnkete)
                return@withContext pitanjaIzBaze
            }
        }
    }
}
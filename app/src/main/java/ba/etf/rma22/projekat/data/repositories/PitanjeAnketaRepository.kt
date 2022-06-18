package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

object PitanjeAnketaRepository {

    var context : Context?=null

    suspend fun getPitanja(idAnkete : Int) : List<Pitanje>{
        return withContext(Dispatchers.IO){
            var response = ApiAdapter.retrofit.getPitanja(idAnkete)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun racunajPrekoOdgovora(idAnkete: Int, idPitanja : Int, odgovor : Int) : Int{
        return withContext(Dispatchers.IO){
         /*   val pokrenuteAnkete = TakeAnketaRepository.getPoceteAnkete()
            var anketaId : Int = 0
            if(pokrenuteAnkete!=null){
                for(anketa in pokrenuteAnkete){
                    if(anketa.id == idAnkete){
                        anketaId = anketa.id
                    }
                }
            }
            val pitanjaAnkete = getPitanja(idAnkete)
            val odgovoriAnkete = OdgovorRepository.getOdgovoriAnketa(idAnkete)
            var vrijednostZaSvakiOdgovor : Float = 100.toFloat()/pitanjaAnkete.size
            var pomocnaVrijednost = vrijednostZaSvakiOdgovor/100
            var vrijednost : Int = 0
            var brojac = 0
            for(pitanje in pitanjaAnkete){
                for(odg in odgovoriAnkete){
                    if(odg.pitanjeId == pitanje.id){
                        brojac++
                    }
                }
            }
            vrijednost  = (pomocnaVrijednost*brojac*10).roundToInt()
            if(vrijednost%2!=0)
                vrijednost+=1
            vrijednost*=10*/
            return@withContext 60
        }
    }

}
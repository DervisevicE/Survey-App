package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.VratiZaOdgovor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

object OdgovorRepository {

    var context: Context?=null

    suspend fun getOdgovoriAnketa(idAnketa: Int) : List<VratiZaOdgovor>{
        return withContext(Dispatchers.IO){
            val pokusaj = TakeAnketaRepository.getPoceteAnkete()?.find { it.AnketumId == idAnketa }
            var listaOdgovora = listOf<VratiZaOdgovor>()
            if(pokusaj!=null){
                listaOdgovora = ApiAdapter.retrofit.getOdgovoriAnketa(pokusaj!!.id)
            }

            return@withContext listaOdgovora
        }
    }

    suspend fun postaviOdgovorAnketa(idAnketa: Int, idPitanje: Int, odgovor: Int) : Int {
       return withContext(Dispatchers.IO){
           val poceteAnkete = ApiAdapter.retrofit.getPoceteAnkete().body()
           val idAnkete = poceteAnkete!!.filter { it.id == idAnketa }.map { it.AnketumId }.first()
           val brojPitanjaAnkete = ApiAdapter.retrofit.getPitanja(idAnkete).body()!!.size.toFloat()
           val odgovorenaPitanja = ApiAdapter.retrofit.getOdgovoriAnketa(idAnketa)
           var brojOdgovorenihPitanja=0
           if(odgovorenaPitanja!=null){
               brojOdgovorenihPitanja = odgovorenaPitanja.size +1
           }
           var vrijednostZaSvakiOdgovor : Float = 100.toFloat()/brojPitanjaAnkete
           var pomocnaVrijednost = vrijednostZaSvakiOdgovor/100
           var vrijednost : Int = (pomocnaVrijednost*brojOdgovorenihPitanja*10).roundToInt()
           if(vrijednost%2!=0) vrijednost+=1
           vrijednost*=10
           var progres = vrijednost

           ApiAdapter.retrofit.postaviOdgovorAnketa(idAnketa, VratiZaOdgovor(odgovor,idPitanje, progres))

           return@withContext progres

       }
    }
}
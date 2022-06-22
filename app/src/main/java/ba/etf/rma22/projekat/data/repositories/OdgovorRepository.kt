package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.VratiZaOdgovor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.ceil
import kotlin.math.roundToInt

object OdgovorRepository {

    suspend fun getOdgovoriAnketaSaServisa(idAnketa: Int) : List<Odgovor>{
        return withContext(Dispatchers.IO){
            val pokusaj = TakeAnketaRepository.getPoceteAnkete()?.find { it.AnketumId == idAnketa }
            var listaOdgovora = listOf<Odgovor>()
            if(pokusaj!=null){
                listaOdgovora = ApiAdapter.retrofit.getOdgovoriAnketa(pokusaj!!.id)
            }

            return@withContext listaOdgovora
        }
    }

    suspend fun getOdg(pokusaj: AnketaTaken) : List<Odgovor>?{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(AnketaRepository.context!!)
            if(MainActivity.connection){
                var saServisa = ApiAdapter.retrofit.getOdgovoriAnketa(pokusaj.id)
                return@withContext saServisa
            }else{
                var izBaze = db.odgovorDao().getOdgovorZaPitanje(pokusaj.AnketumId)
                return@withContext izBaze
            }
        }
    }

    suspend fun getOdgovoriAnketa(idAnketa: Int) : List<Odgovor>?{
        return withContext(Dispatchers.IO){

                var takenAnketa : AnketaTaken? = null
                var svePoceteAnkete = TakeAnketaRepository.getPoceteAnkete()
                if(svePoceteAnkete!=null){
                    for(poceta in svePoceteAnkete){
                        if (poceta.AnketumId==idAnketa){
                            takenAnketa = poceta
                        }
                    }
                }

                var odgovoriZaAnketu : List<Odgovor>? = emptyList()
                if(takenAnketa!=null)
                    odgovoriZaAnketu = getOdg(takenAnketa)
                return@withContext odgovoriZaAnketu
        }
    }

    suspend fun postaviOdgovorAnketa(idAnketa: Int, idPitanje: Int, odgovor: Int) : Int? {
       return withContext(Dispatchers.IO){
           try{

               var db = AppDatabase.getInstance(AnketaRepository.context!!)
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
               var ubaci = Odgovor(1, odgovor, idPitanje)
               db.odgovorDao().insertAll(ubaci)
               return@withContext progres
           } catch (e: Exception){
               return@withContext null
           }
       }
    }
}
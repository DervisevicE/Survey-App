package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {

    suspend fun getIstrazivanja(offset : Int) : List<Istrazivanje> {
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getIstrazivanja(offset)
        }
    }

    suspend fun getIstrazivanja() : List<Istrazivanje>{
        var svaIstrazivanja = mutableListOf<Istrazivanje>()
        var offset = 1
        while(true){
            val brojIstrazivanja : Int
            withContext(Dispatchers.IO){
                val response = ApiAdapter.retrofit.getIstrazivanja(offset)
                brojIstrazivanja = response.size
                svaIstrazivanja = svaIstrazivanja.plus(response) as MutableList<Istrazivanje>
            }
            if(brojIstrazivanja!=5)
                break
            offset++
        }
        return svaIstrazivanja
    }

    suspend fun getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            var svaIstrazivanja = ApiAdapter.retrofit.getIstrazivanja()
            var istrazivanjaPoGodini = mutableListOf<Istrazivanje>()

            for(istrazivanje in svaIstrazivanja){
                if(istrazivanje.godina == godina){
                    istrazivanjaPoGodini.add(istrazivanje)
                }
            }

            return@withContext istrazivanjaPoGodini
        }
    }

    suspend fun getGrupe() : List<Grupa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getGrupe()
        }
    }

    suspend fun getGrupeZaIstrazivanje(idIstrazivanja: Int) : List<Grupa>{
        return withContext(Dispatchers.IO){
            Log.v("poslan id je", idIstrazivanja.toString())
            var sveGrupe = getGrupe()
            Log.v("sve grupe su ", sveGrupe.toString() )
            var grupeZaVratit = mutableListOf<Grupa>()
            if(sveGrupe!=null){
                for (grupa in sveGrupe){
                    if (grupa.istrazivanjeId == idIstrazivanja){
                        Log.v("grupe za vratit su ", grupeZaVratit.toString())
                        grupeZaVratit.add(grupa)
                    }
                }
            }
            return@withContext grupeZaVratit
        }
    }

     suspend fun upisiUGrupu(idGrupa: Int) : Boolean{
        return withContext(Dispatchers.IO){
            val pokusajUpisivanja : String =
                ApiAdapter.retrofit.upisiUGrupu(idGrupa, AccountRepository.getHash()).toString()
            if(pokusajUpisivanja.contains("Ne postoji") || pokusajUpisivanja.contains("not found"))
                return@withContext false
            return@withContext true
        }
    }

    suspend fun getUpisaneGrupe() : List<Grupa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.dajUpisaneGrupe(AccountRepository.getHash())
        }
    }

}
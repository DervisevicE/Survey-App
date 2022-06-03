package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeRepository {

    private var upisani: MutableList<Istrazivanje>
    init {
        upisani = mutableListOf()
        /*upisani = mutableListOf()
        upisani.add(izdvojiIstrazivanje("Moje istrazivanje",1))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 6",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 3",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 6",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 3",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 1",1))*/

    }

  /*  fun izdvojiIstrazivanje(naziv: String, godina: Int): Istrazivanje{
        return getAll().filter { istrazivanje -> istrazivanje.naziv==naziv && istrazivanje.godina==godina }.first()

    }
*/
    /*fun dodajUMojaIstrazivanja(istrazivanje: String, godina: Int) {
        upisani.add(izdvojiIstrazivanje(istrazivanje, godina))
    }*/

   /* fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje>{
        return istrazivanja().filter { istrazivanje -> istrazivanje.godina==godina  }.toList()
    }*/

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

    /*fun getAll(): List<Istrazivanje>{
        return istrazivanja()
    }*/
    suspend fun getAll() : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getIstrazivanja()
        }
    }

    fun getUpisani(): List<Istrazivanje>{
        if(upisani.size==0)
            return emptyList()
        return upisani
    }
}
package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GrupaRepository {

    /*fun getGroupsByIstrazivanje(nazivIstrazivanja: String) : List<Grupa>{
        return grupe().filter { grupa -> grupa.nazivIstrazivanja==nazivIstrazivanja  }.toList()
    }*/

    suspend fun getGroupsByIstrazivanje(istrazivanje: Istrazivanje) : List<Grupa> {
        return withContext(Dispatchers.IO){
            var listaGrupa = mutableListOf<Grupa>()
            var sveGrupe = ApiAdapter.retrofit.getGrupe()
            for(grupa in sveGrupe){
                if(grupa.nazivIstrazivanja === istrazivanje.naziv){
                    listaGrupa.add(grupa)
                }
            }
            return@withContext listaGrupa
        }
    }
}
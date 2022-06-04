/*
package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GrupaRepository {

    suspend fun getGroupsByIstrazivanje(istrazivanje: String) : List<Grupa> {
        return withContext(Dispatchers.IO){
            var listaGrupaZaPrikazat = mutableListOf<Grupa>()
            var sveGrupe = ApiAdapter.retrofit.getGrupe()
            Log.v("poslano je ", istrazivanje)
            Log.v("sve grupe su", sveGrupe.toString())
            for(grupa in sveGrupe){
                var id = grupa.id
                var istrazivanjeGrupe = ApiAdapter.retrofit.dajIstrazivanjaGrupeSaId(id)
                    if(istrazivanjeGrupe.naziv == istrazivanje){
                        listaGrupaZaPrikazat.add(grupa)
                        Log.v("lista grupa za prikazat je ", listaGrupaZaPrikazat.toString())
                    }
            }
            return@withContext listaGrupaZaPrikazat
        }
    }
}*/

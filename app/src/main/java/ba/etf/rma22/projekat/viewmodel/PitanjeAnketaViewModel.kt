package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PitanjeAnketaViewModel {

   /* fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String) : List<Pitanje>{
        return PitanjeAnketaRepository.getPitanja(nazivAnkete, nazivIstrazivanja)
    }

    fun getOdgovori(nazivPitanja : String) : ArrayList<String> {
        return PitanjeAnketaRepository.getOdgovori(nazivPitanja)
    }*/

    fun getPitanja(idAnketa: Int, onSuccess : (pitanja : List<Pitanje>) -> Unit, onError : () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val pitanja = PitanjeAnketaRepository.getPitanja(idAnketa)
            when(pitanja){
                is List<Pitanje> -> onSuccess.invoke(pitanja)
                else -> onError.invoke()
            }
        }
    }
}
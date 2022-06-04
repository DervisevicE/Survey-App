package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IstrazivanjeIGrupaViewModel {

    fun getIstrazivanjeByGodina(godina : Int, onSuccess : (istrazivanja : List<Istrazivanje>) -> Unit, onError : () -> Unit){
        GlobalScope.launch (Dispatchers.Main) {
            val istrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanjeByGodina(godina)
            when(istrazivanja){
                is List<Istrazivanje> -> onSuccess.invoke(istrazivanja)
                else -> onError.invoke()
            }
        }
    }

    fun getAll(onSuccess : (istrazivanja : List<Istrazivanje>) -> Unit, onError : () -> Unit){
        GlobalScope.launch {
            val istrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanja()
            when(istrazivanja){
                is List<Istrazivanje> -> onSuccess.invoke(istrazivanja)
                else -> onError.invoke()
            }
        }
    }

    fun getGrupeZaIstrazivanje(idIstrazivanja: Int, onSuccess : (grupe : List<Grupa>) -> Unit, onError : () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val grupe = IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(idIstrazivanja)
            when(grupe){
                is List<Grupa> -> onSuccess.invoke(grupe)
                else -> onError.invoke()
            }
        }
    }

}
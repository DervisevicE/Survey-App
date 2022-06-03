package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import ba.etf.rma22.projekat.data.repositories.grupe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GrupaViewModel {

    /*fun getGroupsByIstrazivanje(nazivIstrazivanja: String) : List<Grupa>{
        return GrupaRepository.getGroupsByIstrazivanje(nazivIstrazivanja)
    }*/

    fun getGroupsByIstrazivanje(istrazivanje: Istrazivanje, onSuccess : (grupe : List<Grupa>) -> Unit, onError : () -> Unit){
        GlobalScope.launch {
            val grupe = GrupaRepository.getGroupsByIstrazivanje(istrazivanje)
            when(grupe){
                is List<Grupa> -> onSuccess.invoke(grupe)
                else -> onError.invoke()
            }
        }
    }
}
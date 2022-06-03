package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IstrazivanjeViewModel {

   /* fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(godina)
    }*/

    fun getIstrazivanjeByGodina(godina : Int, onSuccess : (istrazivanja : List<Istrazivanje>) -> Unit, onError : () -> Unit){
        GlobalScope.launch {
            val istrazivanja = IstrazivanjeRepository.getIstrazivanjeByGodina(godina)
            when(istrazivanja){
                is List<Istrazivanje> -> onSuccess.invoke(istrazivanja)
                else -> onError.invoke()
            }
        }
    }

   /* fun getAll(): List<Istrazivanje>{
        return IstrazivanjeRepository.getAll()
    }*/

    fun getAll(onSuccess : (istrazivanja : List<Istrazivanje>) -> Unit, onError : () -> Unit){
        GlobalScope.launch {
            val istrazivanja = IstrazivanjeRepository.getAll()
            when(istrazivanja){
                is List<Istrazivanje> -> onSuccess.invoke(istrazivanja)
                else -> onError.invoke()
            }
        }
    }

    fun getUpisani(): List<Istrazivanje>{
        return IstrazivanjeRepository.getUpisani()
    }

   /* fun dodajUMojaIstrazivanja(istrazivanje: String, godina: Int): Unit {
        IstrazivanjeRepository.dodajUMojaIstrazivanja(istrazivanje,godina)
    }*/
}
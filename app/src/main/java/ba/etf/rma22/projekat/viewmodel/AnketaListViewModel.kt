package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnketaListViewModel {

    /*fun getAnkete() : List<Anketa>{
        return AnketaRepository.getAnkete();
    }*/

    fun getAnkete(onSuccess : (ankete : List<Anketa>) -> Unit, onError : () -> Unit){
        GlobalScope.launch {
            val ankete = AnketaRepository.getAll()
            when(ankete){
                is List<Anketa> -> onSuccess.invoke(ankete)
                else -> onError.invoke()
            }
        }
    }

    /*fun getMyAnkete() : List<Anketa>{
        return AnketaRepository.getMyAnkete()
    }*/

    fun getMyAnkete(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit){
        GlobalScope.launch {
            val ankete = AnketaRepository.getUpisane()
            when(ankete){
                is List<Anketa> -> onSuccess.invoke(ankete)
                else -> onError.invoke()
            }
        }
    }

    fun getDone(): List<Anketa>{
        return AnketaRepository.getDone()
    }

    fun getFuture(): List<Anketa> {
        return AnketaRepository.getFuture()
    }

    fun getNotTaken(): List<Anketa> {
        return AnketaRepository.getNotTaken()
    }

    fun dodajUMojeAnkete(istrazivanje: String, grupa: String): Unit {
        AnketaRepository.dodajUMojeAnkete(istrazivanje,grupa)
    }




}
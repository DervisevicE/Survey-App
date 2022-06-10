package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.Dispatchers
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

    fun getDone(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit){
        GlobalScope.launch {
            val ankete = AnketaRepository.getDone()
            when(ankete){
                is List<Anketa> -> onSuccess.invoke(ankete)
                else -> onError.invoke()
            }
        }
    }

    fun getFuture(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit){
        GlobalScope.launch {
            val ankete = AnketaRepository.getFuture()
            when(ankete){
                is List<Anketa> -> onSuccess.invoke(ankete)
                else -> onError.invoke()
            }
        }
    }

    fun getNotTaken(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit){
        GlobalScope.launch {
            val ankete = AnketaRepository.getNotTaken()
            when(ankete){
                is List<Anketa> -> onSuccess.invoke(ankete)
                else -> onError.invoke()
            }
        }
    }

    fun getById(anketaId : Int, onSuccess : (anketa : Anketa) -> Unit, onError : () -> Unit){
        GlobalScope.launch (Dispatchers.Main) {
            val anketa = AnketaRepository.getById(anketaId)
            when(anketa){
                is Anketa -> onSuccess.invoke(anketa)
                else -> onError.invoke()
            }
        }
    }

    /*fun getDone(): List<Anketa>{
        return AnketaRepository.getDone()
    }

    fun getFuture(): List<Anketa> {
        return AnketaRepository.getFuture()
    }

    fun getNotTaken(): List<Anketa> {
        return AnketaRepository.getNotTaken()
    }
*/
    fun dodajUMojeAnkete(istrazivanje: String, grupa: String): Unit {
        //AnketaRepository.dodajUMojeAnkete(istrazivanje,grupa)
    }




}
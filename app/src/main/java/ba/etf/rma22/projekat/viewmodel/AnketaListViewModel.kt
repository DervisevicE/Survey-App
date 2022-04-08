package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository

class AnketaListViewModel {

    fun getAnkete() : List<Anketa>{
        return AnketaRepository.getAnkete();
    }

    fun getMyAnkete() : List<Anketa>{
        return AnketaRepository.getMyAnkete()
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
package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

class IstrazivanjeViewModel {

    fun getIstrazivaneByGodina(godina: Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivaneByGodina(godina)
    }

    fun getAll(): List<Istrazivanje>{
        return IstrazivanjeRepository.getAll()
    }

    fun getUpisani(): List<Istrazivanje>{
        return IstrazivanjeRepository.getUpisani()
    }
}
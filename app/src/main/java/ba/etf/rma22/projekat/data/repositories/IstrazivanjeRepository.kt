package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje

object IstrazivanjeRepository {

    fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje>{
        return istrazivanja().filter { istrazivanje -> istrazivanje.godina==godina  }.toList()
    }

    fun getAll(): List<Istrazivanje>{
        return istrazivanja()
    }

    fun getUpisani(): List<Istrazivanje>{
        return emptyList()
    }
}
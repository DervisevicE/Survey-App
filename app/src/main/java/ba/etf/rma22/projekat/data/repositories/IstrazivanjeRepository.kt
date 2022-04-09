package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje

object IstrazivanjeRepository {

    private var upisani: MutableList<Istrazivanje>
    init {
        upisani = mutableListOf()
        upisani.add(izdvojiIstrazivanje("Moje istrazivanje",1))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 6",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 3",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 6",2))
        upisani.add(izdvojiIstrazivanje("Istrazivanje broj 3",2))

    }

    fun izdvojiIstrazivanje(naziv: String, godina: Int): Istrazivanje{
        return getAll().filter { istrazivanje -> istrazivanje.naziv==naziv && istrazivanje.godina==godina }.first()

    }

    fun dodajUMojaIstrazivanja(istrazivanje: String, godina: Int) {
        upisani.add(izdvojiIstrazivanje(istrazivanje, godina))
    }

    fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje>{
        return istrazivanja().filter { istrazivanje -> istrazivanje.godina==godina  }.toList()
    }

    fun getAll(): List<Istrazivanje>{
        return istrazivanja()
    }

    fun getUpisani(): List<Istrazivanje>{
        if(upisani.size==0)
            return emptyList()
        return upisani
    }
}
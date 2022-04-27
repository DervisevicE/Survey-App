package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.staticdata.pitanja
import ba.etf.rma22.projekat.data.staticdata.pitanjeAnekta

object PitanjeAnketaRepository {

   fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): List<Pitanje>{

        var pitanjaAnkete = pitanjeAnekta().filter { anketaPitanje -> anketaPitanje.anketa == nazivAnkete && anketaPitanje.nazivIstrazivanje == nazivIstrazivanja }.toList()
        var svaPitanja = pitanja()

        var pitanjaZaPrikazat : MutableList<Pitanje> = mutableListOf()

        for(pitanje in svaPitanja){
            for(anketaPitanje in pitanjaAnkete){
                if(pitanje.naziv == anketaPitanje.naziv){
                    pitanjaZaPrikazat.add(pitanje)
                }
            }
        }
        return pitanjaZaPrikazat
    }

/*    fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): List<Pitanje>{

        var pitanjaAnkete = pitanjeAnekta().filter { anketaPitanje -> anketaPitanje.anketa == nazivAnkete && anketaPitanje.nazivIstrazivanje == nazivIstrazivanja }.toList()
        var pitanjaZaPrikazat : MutableList<String> = mutableListOf()
        for(pitanje in pitanjaAnkete)
            pitanjaZaPrikazat.add(pitanje.naziv)
        return pitanja().filter { x -> pitanjaZaPrikazat.contains(x.naziv) }
    }*/


}
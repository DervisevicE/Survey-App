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

    fun getOdgovori(nazivPitanja : String) : ArrayList<String> {
        var pitanje = pitanja().filter { pitanje -> pitanje.naziv == nazivPitanja }.toList()
        var moguciOdgovori : ArrayList<String> = arrayListOf()

        for(o in pitanje){
            var opcije : MutableList<String> = mutableListOf()
            for(odg in o.opcije){
                moguciOdgovori.add(odg)
            }
        }

        return moguciOdgovori
    }

}
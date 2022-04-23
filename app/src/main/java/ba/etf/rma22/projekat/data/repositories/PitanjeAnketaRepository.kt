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

        var pitanjaAnkete = pitanjeAnekta().filter { anketaPitanje -> anketaPitanje.naziv == nazivAnkete && anketaPitanje.nazivIstrazivanje == nazivIstrazivanja }.toList()
        var svaPitanja = pitanja()

        var pitanjaZaPrikazat = mutableListOf<Pitanje>()

        for(pitanje in svaPitanja){
            for(anketaPitanje in pitanjaAnkete){
                if(pitanje.naziv == anketaPitanje.naziv){
                    pitanjaZaPrikazat.add(pitanje)
                }
            }
        }

        /*var izdvojiAnketePoIstrazivanju = ankete().filter { anketa -> anketa.naziv == nazivAnkete
                && anketa.nazivIstrazivanja == nazivIstrazivanja}.toList()

        var izdvojiPitanjaAnketa  = pitanjeAnekta().filter { anketa -> anketa.naziv == nazivAnkete }.toList()

        var pogodnePitanjaAnkete = mutableListOf<PitanjeAnketa>()

        for(anketaPoIstrazivanju in izdvojiAnketePoIstrazivanju){
            for(anketaPoNazivu in izdvojiPitanjaAnketa){
                if(anketaPoIstrazivanju.naziv===anketaPoNazivu.naziv){
                    pogodnePitanjaAnkete.add(anketaPoNazivu)
                }
            }
        }

        var pitanjaZaPrikazat = mutableListOf<Pitanje>()
        var dajPitanja = pitanja()

        for(pitanje in dajPitanja){
            for (anketaPitanje in pogodnePitanjaAnkete){
                if(pitanje.naziv == anketaPitanje.naziv){
                    pitanjaZaPrikazat.add(pitanje)
                }
            }
        }*/
        return pitanjaZaPrikazat
    }
}
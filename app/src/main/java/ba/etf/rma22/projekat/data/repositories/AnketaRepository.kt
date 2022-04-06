package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

object AnketaRepository {
    fun getAnkete() : List<Anketa>{
        return ankete()
    }
    fun getAll(): List<Anketa> {
        return ankete()
    }

    fun getMyAnkete(): List<Anketa> {
        return emptyList()
    }

    fun getDone(): List<Anketa> {
        return ankete().filter { anketa -> dajStatus(anketa)=="plava" }.toList()

    }

    fun getFuture(): List<Anketa> {
        return ankete().filter { anketa -> dajStatus(anketa)=="zuta" }.toList()
    }

    fun getNotTaken(): List<Anketa> {
        return ankete().filter { anketa -> dajStatus(anketa) == "crvena" }.toList()
    }

    private fun dajStatus(anketa: Anketa): String {

        var cal: Calendar = Calendar.getInstance()
        cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
        //var date: Date = Calendar.getInstance().time

        if(anketa.datumRada==null){
            if(anketa.datumPocetka.before(date) && anketa.datumKraja.after(date)) return "zelena"
            else if(anketa.datumKraja.before(date) && anketa.datumKraja.before(date)) return "crvena"
            else if (anketa.datumPocetka.after(date) && anketa.datumKraja.after(date)) return "zuta"
        }
        return "plava"
    }

}
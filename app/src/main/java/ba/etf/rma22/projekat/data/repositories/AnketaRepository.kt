package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.data.staticdata.pitanja
import ba.etf.rma22.projekat.data.staticdata.pitanjeAnekta
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

object AnketaRepository {

    private var mojeAnkete: MutableList<Anketa>

    fun izdvojiAnketu(istrazivanje: String, grupa: String): Anketa {
        return getAll().filter { anketa ->
            anketa.nazivIstrazivanja == istrazivanje &&
                    anketa.nazivGrupe == grupa
        }.first()
    }

    fun dodajUMojeAnkete(istrazivanje: String, grupa: String): Unit {
        mojeAnkete.add(izdvojiAnketu(istrazivanje, grupa))
    }

    init {
        mojeAnkete = mutableListOf()
        mojeAnkete.add(izdvojiAnketu("Moje istrazivanje", "G1"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 6", "G1"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 6", "G4"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 3", "G3"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 3", "G1"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 1", "G3"))

    }

    fun getAnkete(): List<Anketa> {
        return ankete()
    }

    fun getAll(): List<Anketa> {
        return ankete()
    }

    fun getMyAnkete(): List<Anketa> {
        if (mojeAnkete.size == 0)
            return emptyList()
        return mojeAnkete
    }

    fun getDone(): List<Anketa> {
        return mojeAnkete.filter { anketa -> dajStatus(anketa) == "plava" }.ifEmpty { emptyList() }
    }

    fun getFuture(): List<Anketa> {
        return mojeAnkete.filter { anketa -> dajStatus(anketa) == "zuta" }.ifEmpty { emptyList() }
    }

    fun getNotTaken(): List<Anketa> {
        return mojeAnkete.filter { anketa -> dajStatus(anketa) == "crvena" }.ifEmpty { emptyList() }
    }

    private fun dajStatus(anketa: Anketa): String {

        var cal: Calendar = Calendar.getInstance()
        cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
        //var date: Date = Calendar.getInstance().time

        if (anketa.datumRada == null) {
            if (anketa.datumPocetka.before(date) && anketa.datumKraja.after(date)) return "zelena"
            else if (anketa.datumKraja.before(date) && anketa.datumKraja.before(date)) return "crvena"
            else if (anketa.datumPocetka.after(date) && anketa.datumKraja.after(date)) return "zuta"
        }
        return "plava"
    }

}
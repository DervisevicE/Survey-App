package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

object AnketaRepository {

    private var mojeAnkete: MutableList<Anketa>

   /* fun izdvojiAnketu(istrazivanje: String, grupa: String): Anketa {
        return getAll1().filter { anketa ->
            anketa.nazivIstrazivanja == istrazivanje &&
                    anketa.nazivGrupe == grupa
        }.first()
    }*/

   /* fun dodajUMojeAnkete(istrazivanje: String, grupa: String): Unit {
        mojeAnkete.add(izdvojiAnketu(istrazivanje, grupa))
    }*/

    init {
        mojeAnkete = mutableListOf()
        /*mojeAnkete.add(izdvojiAnketu("Moje istrazivanje", "G1"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 6", "G1"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 6", "G4"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 3", "G3"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 3", "G1"))
        mojeAnkete.add(izdvojiAnketu("Istrazivanje broj 1", "G3"))
*/
    }


    private fun dajStatus(anketa: Anketa): String {

        var cal: Calendar = Calendar.getInstance()
        cal.set(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)
        var date: Date = cal.time
        //var date: Date = Calendar.getInstance().time

        if (anketa.datumRada == null) {
            if (anketa.datumPocetak.before(date) && anketa.datumKraj.after(date)) return "zelena"
            else if (anketa.datumKraj.before(date) && anketa.datumKraj.before(date)) return "crvena"
            else if (anketa.datumPocetak.after(date) && anketa.datumKraj.after(date)) return "zuta"
        }
        return "plava"
    }

    suspend fun getAll(offset: Int) : List<Anketa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getAllAnkete(offset)
        }
    }

    suspend fun getAll() : List<Anketa>{
        var sveAnkete = mutableListOf<Anketa>()
        var offset = 1
        while (true){
            val brojAnketa : Int
            withContext(Dispatchers.IO){
                val resposne = ApiAdapter.retrofit.getAllAnkete(offset)
                brojAnketa = resposne.size
                sveAnkete = sveAnkete.plus(resposne) as MutableList<Anketa>
            }
            if (brojAnketa!=5)
                break
            offset++
        }

        return sveAnkete
    }

    suspend fun getById(id: Int) : Anketa? {
        return withContext(Dispatchers.IO){
            try {
                return@withContext ApiAdapter.retrofit.getById(id)
            } catch (e: Exception){
                return@withContext null
            }
        }
    }

    suspend fun getUpisane() : List<Anketa>{
        return withContext(Dispatchers.IO){
            var anketeZaUpisaneGrupe = mutableListOf<Anketa>()

            var upisaneGrupe = ApiAdapter.retrofit.dajUpisaneGrupe(AccountRepository.getHash())
            for(grupa in upisaneGrupe){
                var pom = ApiAdapter.retrofit.getUpisane(grupa.id)
                for(anketa in pom){
                    anketeZaUpisaneGrupe.add(anketa)
                }
            }
            return@withContext anketeZaUpisaneGrupe
        }
    }

     suspend fun getDone() : List<Anketa>{
        return getUpisane().filter { anketa -> dajStatus(anketa) == "plava" }.ifEmpty { emptyList() }
    }

     suspend fun getFuture(): List<Anketa> {
        return getUpisane().filter { anketa -> dajStatus(anketa) == "zuta" }.ifEmpty { emptyList() }
    }

     suspend fun getNotTaken(): List<Anketa> {
        return getUpisane().filter { anketa -> dajStatus(anketa) == "crvena" }.ifEmpty { emptyList() }
    }
}
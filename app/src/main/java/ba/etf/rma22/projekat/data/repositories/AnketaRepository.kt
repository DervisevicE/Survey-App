package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.room.Room
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

object AnketaRepository {

    private var mojeAnkete: MutableList<Anketa>
    var context: Context?=null
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

        /*if (anketa.datumRada == null) {
            if (anketa.datumPocetak.before(date) && anketa.datumKraj.after(date)) return "zelena"
            else if (anketa.datumKraj.before(date) && anketa.datumKraj.before(date)) return "crvena"
            else if (anketa.datumPocetak.after(date) && anketa.datumKraj.after(date)) return "zuta"
        }*/
        return "plava"
    }

   suspend fun getAllSaServisa(offset: Int) : List<Anketa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getAllAnkete(offset)
        }
    }

    suspend fun getAllSaServisa() : List<Anketa>{
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
        //Log.v("SA SERVISA FJA", sveAnkete.size.toString())
        return sveAnkete
    }

    suspend fun getAll() : List<Anketa>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if(MainActivity.connection){
                var anketeSaServisa = getAllSaServisa()
                var upisaneAnkete = getUpisaneSaServisa()

                for(anketa in anketeSaServisa){
                    if(upisaneAnkete.size!=0){
                        for(upisana in upisaneAnkete){
                            if(anketa==upisana)
                                anketa.upisana=1
                    }
                        db.anketaDao().insertAll(anketa)
                        Log.v("BVNSDJVNSDJFNSDŠ", "")
                    }
                }
                return@withContext anketeSaServisa
            }else{
                var anketeSaBaze = db.anketaDao().getAll()
                return@withContext anketeSaBaze
            }
        }
    }

    suspend fun getByIdSaServera(id: Int) : Anketa? {
        return withContext(Dispatchers.IO){
            try {
                return@withContext ApiAdapter.retrofit.getById(id)
            } catch (e: Exception){
                return@withContext null
            }
        }
    }

    suspend fun getById(id: Int) : Anketa{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if(MainActivity.connection){
                var anketa = getByIdSaServera(id)
                db.anketaDao().insertAll(anketa!!)
                return@withContext anketa
            }else{
                var anketaSaIdIzBaze = db.anketaDao().getAnketaById(id)
                return@withContext anketaSaIdIzBaze
            }
        }
    }

    suspend fun getUpisaneSaServisa() : List<Anketa>{
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

    suspend fun getUpisane() : List<Anketa>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            Log.v("SAD SAM U UPISANIM", "")

            if(MainActivity.connection){
                var upisaneSaServisa = getUpisaneSaServisa()
                Log.v("SIZE UPISANIH SA SERVISA U OVOJ FJI JE", upisaneSaServisa.size.toString())
                return@withContext upisaneSaServisa
            }else{
                var anketeIzBaze = db.anketaDao().getUpisaneAnkete()
                return@withContext anketeIzBaze
            }
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
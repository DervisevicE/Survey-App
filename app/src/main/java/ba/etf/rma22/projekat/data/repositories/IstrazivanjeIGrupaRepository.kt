package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {

    var context: Context?=null


    suspend fun getIstrazivanja(offset : Int) : List<Istrazivanje> {
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getIstrazivanja(offset)
        }
    }

    suspend fun getIstrazivanjaSaServisa() : List<Istrazivanje>{
        var svaIstrazivanja = mutableListOf<Istrazivanje>()
        var offset = 1
        while(true){
            val brojIstrazivanja : Int
            withContext(Dispatchers.IO){
                val response = ApiAdapter.retrofit.getIstrazivanja(offset)
                brojIstrazivanja = response.size
                svaIstrazivanja = svaIstrazivanja.plus(response) as MutableList<Istrazivanje>
            }
            if(brojIstrazivanja!=5)
                break
            offset++
        }
        return svaIstrazivanja
    }

    suspend fun getIstrazivanja() : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if(MainActivity.connection){
                var istrazivanjaSaServisa = getIstrazivanjaSaServisa()

                for(istrazivanje in istrazivanjaSaServisa){
                    db.istrazivanjeDao().insertAll(istrazivanje)
                }
                return@withContext istrazivanjaSaServisa
            }else{
                var istrazivanjaIzBaze = db.istrazivanjeDao().getIstrazivanja()
                return@withContext istrazivanjaIzBaze
            }
        }
    }

    suspend fun getIstrazivanjeByGodinaSaServisa(godina:Int) : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            var svaIstrazivanja = ApiAdapter.retrofit.getIstrazivanja()
            var istrazivanjaPoGodini = mutableListOf<Istrazivanje>()

            for(istrazivanje in svaIstrazivanja){
                if(istrazivanje.godina == godina){
                    istrazivanjaPoGodini.add(istrazivanje)
                }
            }
            return@withContext istrazivanjaPoGodini
        }
    }

    suspend fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if (MainActivity.connection){
                var saServisa = getIstrazivanjeByGodinaSaServisa(godina)
                for(istrazivanje in saServisa)
                    db.istrazivanjeDao().insertAll(istrazivanje)
                return@withContext saServisa
            }else{
                var izBaze = db.istrazivanjeDao().getIstrazivanjeByGodina(godina)
                return@withContext izBaze
            }
        }
    }

    suspend fun getGrupeSaServisa() : List<Grupa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getGrupe()
        }
    }

    suspend fun getGrupe() : List<Grupa>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if (MainActivity.connection){
                var saServisa = getGrupeSaServisa()
                var upisaneSaServisa = getUpisaneGrupeSaServisa()

                for(grupa in saServisa){
                    if(upisaneSaServisa.size!=0) {
                        for (upisana in upisaneSaServisa) {
                            if (grupa == upisana)
                                grupa.upisana = 1
                        }
                    }
                    db.grupaDao().insertAll(grupa)
                }
                return@withContext saServisa
            }else{
                var izBaze = db.grupaDao().getGrupe()
                return@withContext izBaze
            }
        }
    }

    suspend fun getGrupeZaIstrazivanje(idIstrazivanja: Int) : List<Grupa>{
        return withContext(Dispatchers.IO){
            var sveGrupe = getGrupe()
            var grupeZaVratit = mutableListOf<Grupa>()
            if(sveGrupe!=null){
                for (grupa in sveGrupe){
                    if (grupa.istrazivanjeId == idIstrazivanja){
                        grupeZaVratit.add(grupa)
                    }
                }
            }
            return@withContext grupeZaVratit
        }
    }

     suspend fun upisiUGrupu(idGrupa: Int) : Boolean{
        return withContext(Dispatchers.IO){
            if(MainActivity.connection){
                val pokusajUpisivanja : String =
                    ApiAdapter.retrofit.upisiUGrupu(idGrupa, AccountRepository.getHash()).toString()
                if(pokusajUpisivanja.contains("Ne postoji") || pokusajUpisivanja.contains("not found"))
                    return@withContext false
                return@withContext true
            }else{
                return@withContext false
            }
        }
    }

    suspend fun getUpisaneGrupeSaServisa() : List<Grupa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.dajUpisaneGrupe(AccountRepository.getHash())
        }
    }

    suspend fun getUpisaneGrupe() : List<Grupa>{
        return withContext(Dispatchers.IO){
            var db = AppDatabase.getInstance(context!!)
            if(MainActivity.connection){
                var saServisa = getGrupeSaServisa()

                for(grupa in saServisa){
                    grupa.upisana = 1
                    db.grupaDao().insertAll(grupa)
                }

                return@withContext saServisa
            }else{
                var izBaze = db.grupaDao().getUpisane()
                return@withContext izBaze
            }
        }
    }
}
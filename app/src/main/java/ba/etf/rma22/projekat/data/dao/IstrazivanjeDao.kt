package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje

@Dao
interface IstrazivanjeDao {

    @Query("SELECT * FROM istrazivanje")
    suspend fun getIstrazivanja() : List<Istrazivanje>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg istrazivanje: Istrazivanje)

    @Query("SELECT * FROM istrazivanje WHERE godina =:godina")
    suspend fun getIstrazivanjeByGodina(godina : Int) : List<Istrazivanje>
}
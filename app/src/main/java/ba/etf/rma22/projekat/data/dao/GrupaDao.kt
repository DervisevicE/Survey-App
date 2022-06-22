package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

@Dao
interface GrupaDao {

    @Query("SELECT * FROM grupa")
    suspend fun getGrupe() : List<Grupa>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg grupa: Grupa)

    @Query("SELECT * FROM grupa WHERE upisana=1")
    suspend fun getUpisane() : List<Grupa>
}
package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.AnketaTaken

@Dao
interface AnketaTakenDao {

    @Query("SELECT * FROM anketataken")
    suspend fun getPocete() : List<AnketaTaken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg anketaTaken: AnketaTaken)
}
package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Pitanje

@Dao
interface PitanjeDao {

    @Query("SELECT * FROM pitanje WHERE anketumId = :idAnkete")
    suspend fun getPitanja(idAnkete: Int) : List<Pitanje>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun ubaciPitanje(vararg pitanje: Pitanje)
}
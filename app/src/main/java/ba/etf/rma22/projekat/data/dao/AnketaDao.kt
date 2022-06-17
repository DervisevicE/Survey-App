package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa

@Dao
interface AnketaDao {
    @Query("SELECT * FROM anketa")
    suspend fun getAll() : List<Anketa>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg ankete: Anketa)

    @Query("SELECT * FROM anketa WHERE id =:anketaId")
    suspend fun getAnketaById(anketaId : Int) : Anketa

    @Query("SELECT * FROM anketa WHERE upisana IS 1")
    suspend fun getUpisaneAnkete() : List<Anketa>
}
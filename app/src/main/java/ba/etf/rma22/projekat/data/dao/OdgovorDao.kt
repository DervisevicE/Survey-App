package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.VratiZaOdgovor

@Dao
interface OdgovorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg odgovor: Odgovor)

    @Query("SELECT * FROM odgovor WHERE PitanjeId = :idPitanja")
    suspend fun getOdgovorZaPitanje(idPitanja : Int) : List<Odgovor>

}
package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    suspend fun getAccount() : Account

    @Insert
    suspend fun insertAccount(vararg acc: Account)

    @Query("DELETE FROM account")
    suspend fun obrisiAccount()

}
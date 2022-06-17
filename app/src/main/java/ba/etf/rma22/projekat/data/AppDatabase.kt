package ba.etf.rma22.projekat.data

import android.content.Context
import androidx.room.*
import ba.etf.rma22.projekat.data.dao.AccountDao
import ba.etf.rma22.projekat.data.dao.AnketaDao
import ba.etf.rma22.projekat.data.dao.Converter
import ba.etf.rma22.projekat.data.models.Account
import ba.etf.rma22.projekat.data.models.Anketa

@Database(entities = arrayOf(Account::class, Anketa::class), version = 4 )
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun anketaDao() : AnketaDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA22DB"
            ).fallbackToDestructiveMigration().build()
    }
}
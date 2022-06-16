package ba.etf.rma22.projekat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ba.etf.rma22.projekat.data.dao.AccountDao
import ba.etf.rma22.projekat.data.models.Account

@Database(entities = arrayOf(Account::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

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
            ).build()
    }
}
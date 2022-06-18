package ba.etf.rma22.projekat.data

import android.content.Context
import androidx.room.*
import ba.etf.rma22.projekat.data.dao.*
import ba.etf.rma22.projekat.data.models.*

@Database(entities = arrayOf(Account::class, Anketa::class, Pitanje::class, Istrazivanje::class, Grupa::class, AnketaTaken::class, Odgovor::class), version = 12 )
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun anketaDao() : AnketaDao
    abstract fun pitanjeDao() : PitanjeDao
    abstract fun istrazivanjeDao() : IstrazivanjeDao
    abstract fun grupaDao() : GrupaDao
    abstract fun anketaTakenDao() : AnketaTakenDao
    abstract fun odgovorDao() : OdgovorDao

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
package org.d3if3049.mobpro1.parkirku.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3049.mobpro1.parkirku.model.History

@Database(entities = [History::class], exportSchema = false, version = 1)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile

        private var INSTANCE:HistoryDatabase? = null

        @JvmStatic
        fun getInstance(context: Context):HistoryDatabase {
            if (INSTANCE == null) {
                synchronized(HistoryDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "History.db"
                    ).build()
                }
            }
            return INSTANCE as HistoryDatabase
        }
    }
}
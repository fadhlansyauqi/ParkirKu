package org.d3if3049.mobpro1.parkirku.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.d3if3049.mobpro1.parkirku.model.History

@Database(entities = [History::class], exportSchema = false, version = 1)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
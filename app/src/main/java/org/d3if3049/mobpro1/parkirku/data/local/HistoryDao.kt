package org.d3if3049.mobpro1.parkirku.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.d3if3049.mobpro1.parkirku.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)
}
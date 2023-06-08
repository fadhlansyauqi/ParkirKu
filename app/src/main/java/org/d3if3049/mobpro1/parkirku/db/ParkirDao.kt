package org.d3if3049.mobpro1.parkirku.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ParkirDao {

    @Insert
    fun insert(parkir: ParkirEntity)

    @Query("SELECT * FROM parkir ORDER BY id DESC")
    fun getLastParkir(): LiveData<List<ParkirEntity?>>
}
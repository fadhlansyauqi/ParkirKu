package org.d3if3049.mobpro1.parkirku.model

import androidx.room.Entity
import com.squareup.moshi.Json
import java.sql.Timestamp

@Entity(tableName = "history")
data class History(
    @field:Json(name="jenisKendaraan")
    val jenisKendaraan:String,

    @field:Json(name="durasiParkir")
    val durasiParkir:Int,

    @field:Json(name="biayaParkir")
    val biayaParkir:Int,

    @field:Json(name="tanggal")
    val tanggal:Timestamp
)

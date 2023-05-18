package org.d3if3049.mobpro1.parkirku.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.sql.Timestamp

@Entity(tableName = "history")
data class History(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "jenisKendaraan")
    val jenisKendaraan:String,

    @ColumnInfo(name = "durasiParkir")
    val durasiParkir:Int,

    @ColumnInfo(name = "biayaParkir")
    val biayaParkir:Int,

    @ColumnInfo(name = "tanggal")
    val tanggal:String,

    @ColumnInfo(name = "isFavorite")
    var isHistory:Boolean
)

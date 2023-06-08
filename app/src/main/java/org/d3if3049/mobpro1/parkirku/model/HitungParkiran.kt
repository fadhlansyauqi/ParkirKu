package org.d3if3049.mobpro1.parkirku.model

import org.d3if3049.mobpro1.parkirku.db.ParkirEntity

fun ParkirEntity.hitungParkiran(): HasilHitung {
    val jam = jam
    val jenisKendaraan = if (jenis.equals("Motor", ignoreCase = true)) {
        jam * 2000
    } else if (jenis.equals("Mobil", ignoreCase = true)) {
        jam * 5000
    } else {
        0
    }
    return HasilHitung(jam, jenisKendaraan)
}
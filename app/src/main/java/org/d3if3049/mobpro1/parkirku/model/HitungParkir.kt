package org.d3if3049.mobpro1.parkirku.model


import org.d3if3049.mobpro1.parkirku.db.ParkirEntity


fun ParkirEntity.hitungPemakaian(): HasilHitung {
    val jam = jam
    val tipekomputer = if (tipe.equals("Motor", ignoreCase = true)) {
        jam * 2000
    } else if (tipe.equals("Mobil", ignoreCase = true)) {
        jam * 5000
    } else {
        0
    }

    return HasilHitung(jam, tipekomputer)
}
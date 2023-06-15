package org.d3if3049.mobpro1.parkirku.model

import org.d3if3049.ParkirKu.R

object BiayaParkirObject {
    fun getBiayaParkir(): List <BiayaParkir> {
        return listOf(
            BiayaParkir(R.drawable.logo_bec, "Bandung Electronic Center", "Parkir motor RP3000", "Parkir mobil Rp5000")
        )
    }
}
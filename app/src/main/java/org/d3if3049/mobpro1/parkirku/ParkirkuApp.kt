package org.d3if3049.mobpro1.parkirku

import android.app.Application
import android.content.Context

class ParkirkuApp : Application() {
    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}
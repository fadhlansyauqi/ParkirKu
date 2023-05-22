package org.d3if3049.mobpro1.parkirku.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3049.mobpro1.parkirku.ui.history.ViewModelHistory
import org.d3if3049.mobpro1.parkirku.factory.main.ViewModelMain

class ViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ViewModelHistory::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return ViewModelHistory(application) as T
            }
            modelClass.isAssignableFrom(ViewModelMain::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return ViewModelMain(application) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
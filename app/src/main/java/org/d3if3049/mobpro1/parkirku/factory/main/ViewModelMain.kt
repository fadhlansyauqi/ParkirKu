package org.d3if3049.mobpro1.parkirku.factory.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.d3if3049.mobpro1.parkirku.data.repository.Repository
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDatabase
import org.d3if3049.mobpro1.parkirku.model.History

class ViewModelMain(application: Application): AndroidViewModel(application) {
    // inisialisasi repository
    private val repository: Repository

    init {
        val historyDao = HistoryDatabase.getInstance(application).historyDao()
        repository = Repository(historyDao)
    }

    suspend fun insertHistory(history: History) {
        repository.insertHistory(history)
    }
}
package org.d3if3049.mobpro1.parkirku.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import org.d3if3049.mobpro1.parkirku.data.repository.Repository
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDatabase
import org.d3if3049.mobpro1.parkirku.model.History

class ViewModelHistory(application: Application): AndroidViewModel(application) {
    // inisialisasi repository
    private val repository: Repository
    // getAllHistory

    init {
        val historyDao = HistoryDatabase.getInstance(application).historyDao()
        repository = Repository(historyDao)
    }
    suspend fun getAllHistory(): LiveData<List<History>> {
        return repository.getAllHistory()
    }

    suspend fun deleteHistory(history: History) {
        repository.deleteHistory(history)
    }
}
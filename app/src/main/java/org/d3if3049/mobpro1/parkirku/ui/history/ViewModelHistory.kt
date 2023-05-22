package org.d3if3049.mobpro1.parkirku.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun getAllHistory(): LiveData<List<History>> {
        return repository.getAllHistory()
    }

    fun deleteHistory(history: History) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteHistory(history)
        }
    }
}
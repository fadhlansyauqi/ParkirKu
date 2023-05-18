package org.d3if3049.mobpro1.parkirku.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.data.Repository
import org.d3if3049.mobpro1.parkirku.model.History

class ViewModelHistory(application: Application): AndroidViewModel(application) {
    // inisialisasi repository
    private val repository = Repository(application)
    // getAllHistory
    suspend fun getAllHistory() = repository.getAllHistory()

    fun deleteHistory(history: History) = viewModelScope.launch {
        repository.deleteHistory(history)
    }
}
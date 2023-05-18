package org.d3if3049.mobpro1.parkirku.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.data.Repository
import org.d3if3049.mobpro1.parkirku.model.History

class ViewModelMain(application: Application): AndroidViewModel(application) {
    // inisialisasi repository
    private val repository = Repository(application)

    fun insertHistory(history: History) = viewModelScope.launch {
        repository.insertHistory(history)
    }
}
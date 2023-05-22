package org.d3if3049.mobpro1.parkirku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDao
import org.d3if3049.mobpro1.parkirku.model.History

class Repository(private val dao:HistoryDao) {

    val listHistory: LiveData<List<History>> = dao.getAllHistory().asLiveData()

    fun getAllHistory(): LiveData<List<History>> {
        return listHistory
    }

    suspend fun insertHistory(history: History) = withContext(Dispatchers.IO) {
        dao.insertHistory(history)

    }

    fun deleteHistory(history: History) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteHistory(history)
        }
    }
}
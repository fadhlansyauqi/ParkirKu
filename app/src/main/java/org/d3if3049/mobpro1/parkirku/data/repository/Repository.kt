package org.d3if3049.mobpro1.parkirku.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDao
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDatabase
import org.d3if3049.mobpro1.parkirku.model.History

class Repository(private val dao:HistoryDao) {

    private val listHistory = MutableLiveData<List<History>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val list = dao.getAllHistory()
            listHistory.postValue(list)
        }
    }

    fun getAllHistory(): LiveData<List<History>> {
        return listHistory
    }

    suspend fun insertHistory(history: History) = withContext(Dispatchers.IO) {
        dao.insertHistory(history)
    }

    suspend fun deleteHistory(history: History) = withContext(Dispatchers.IO) {
        dao.deleteHistory(history)
    }
}
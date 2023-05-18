package org.d3if3049.mobpro1.parkirku.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDao
import org.d3if3049.mobpro1.parkirku.data.local.HistoryDatabase
import org.d3if3049.mobpro1.parkirku.model.History

class Repository(application: Application) {
    private val dao:HistoryDao

    init {
        val database: HistoryDatabase = HistoryDatabase.getInstance(application)
        dao = database.historyDao()
    }

    suspend fun getAllHistory(): LiveData<List<History>> {
        val listHistory = MutableLiveData<List<History>>()

        if (dao.getAllHistory().isEmpty())listHistory.postValue((null))
        else listHistory.postValue(dao.getAllHistory())

        return listHistory
    }
    suspend fun insertHistory(history: History) = dao.insertHistory(history)

    suspend fun deleteHistory(history: History) = dao.deleteHistory(history)
}
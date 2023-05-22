package org.d3if3049.mobpro1.parkirku.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    companion object {
        val LAYOUT_PREF = stringPreferencesKey("layout")
    }

    suspend fun saveToDataStore(layout: String?) {
        context.dataStore.edit {
            it[LAYOUT_PREF] = layout ?: ""
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        it[LAYOUT_PREF] ?: ""
    }

    suspend fun deleteSession() = context.dataStore.edit {
        it.clear()
    }
}
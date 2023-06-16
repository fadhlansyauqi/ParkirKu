package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.model.BiayaParkir
import org.d3if3049.mobpro1.parkirku.network.BiayaParkirApi

class BiayaParkirViewModel : ViewModel(){
    private val data = MutableLiveData<List<BiayaParkir>>()
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = BiayaParkirApi.service.getBiayaParkir()
                Log.d("MainViewModel", "Success: $result")
                data.postValue(BiayaParkirApi.service.getBiayaParkir())
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}
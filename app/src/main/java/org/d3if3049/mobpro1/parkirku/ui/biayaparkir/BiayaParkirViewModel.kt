package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.model.BiayaParkir
import org.d3if3049.mobpro1.parkirku.network.ApiStatus
import org.d3if3049.mobpro1.parkirku.network.BiayaParkirApi

class BiayaParkirViewModel : ViewModel(){
    private val data = MutableLiveData<List<BiayaParkir>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                val result = BiayaParkirApi.service.getBiayaParkir()
                Log.d("MainViewModel", "Success: $result")
                data.postValue(BiayaParkirApi.service.getBiayaParkir())
                status.postValue(ApiStatus.SUCCES)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<BiayaParkir>> = data

    fun getStatus(): LiveData<ApiStatus> = status
}
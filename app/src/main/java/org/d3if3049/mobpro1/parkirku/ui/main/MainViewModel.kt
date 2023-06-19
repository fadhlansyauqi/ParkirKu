package org.d3if3049.mobpro1.parkirku.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3049.mobpro1.parkirku.db.ParkirDao
import org.d3if3049.mobpro1.parkirku.db.ParkirEntity
import org.d3if3049.mobpro1.parkirku.model.HasilHitung
import org.d3if3049.mobpro1.parkirku.model.Parkir

class MainViewModel(private val db: ParkirDao): ViewModel() {
    private val hasilHitung = MutableLiveData<HasilHitung>()

    fun tampungBiayaParkir(jam: Int, tipe: String){
        val hitungParkir1 = Parkir (
            jam = jam,
            tipe = tipe)
        hasilHitung.value = hitungParkir1.hitungPemakaian()
    }

    private fun Parkir.hitungPemakaian(): HasilHitung {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataParkir = ParkirEntity(
                    jam = jam,
                    tipe = tipe
                )
                db.insert(dataParkir)
            }
        }
        val jam = jam
        val jurusanparkir = if (tipe.equals("Motor" , ignoreCase = true)) {
            jam * 2000
        } else if (tipe.equals("Mobil" , ignoreCase = true)) {
            jam * 5000
        } else {
            0
        }
        return HasilHitung(jam, jurusanparkir)
    }

    fun getBiayaParkir(): LiveData<HasilHitung?> = hasilHitung
}
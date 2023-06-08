package org.d3if3049.mobpro1.parkirku.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3049.mobpro1.parkirku.model.HasilHitung
import org.d3if3049.mobpro1.parkirku.model.Parkir

class MainViewModel(): ViewModel() {
    private val hasilHitung = MutableLiveData<HasilHitung>()

    fun tampungParkiran(jam: Int, jenis: String){
        val hitungParkir = Parkir(
            jam = jam,
            jenis = jenis
        )
        hasilHitung.value = hitungParkir.hitungParkiran()
    }
    fun Parkir.hitungParkiran(): HasilHitung{
        val jam = jam
        val jenisKendaraan = if (jenis.equals("Motor", ignoreCase = true)){
            jam * 2000
        } else if (jenis.equals("Mobil", ignoreCase = true)){
            jam * 5000
        }else{
            0
        }
        return HasilHitung(jam, jenisKendaraan)
    }
}
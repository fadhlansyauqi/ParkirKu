package org.d3if3049.mobpro1.parkirku

import android.os.Bundle
import android.text.TextUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.d3if3049.mobpro1.parkirku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var selectedUnitLayout: LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var selectedUnit: String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedUnitLayout = binding.selectType
        selectedUnitText = binding.textSelect

//        val resultTypeText = binding.textResultType



        selectedUnit = ""

        selectedUnitLayout.setOnClickListener {
            showAlertDialog()
        }


        binding.buttonHitungBiaya.setOnClickListener { hitungParkir() }
    }

    private fun hitungParkir(){
        val editInput = binding.editInput.text.toString()

            if (TextUtils.isEmpty(editInput)) {
                Toast.makeText(this, R.string.alertInputKosong, Toast.LENGTH_LONG ).show()
                return
            }
            if (editInput.isNotEmpty()) {
                val input = editInput.toInt()

                if (selectedUnit == "Motor") {
                    val hasil = input * 2000
                    binding.textResult.text = "Rp $hasil"
                    binding.textResultType.text = "Total Biaya Parkir Motor Anda Adalah:"
                } else {
                    val hasil = input * 5000
                    binding.textResult.text = "Rp $hasil"
                    binding.textResultType.text = "Total Biaya Parkir Mobil Anda Adalah:"
                }

                Toast.makeText(applicationContext, "Biaya Parkir Ditampilkan", Toast.LENGTH_SHORT).show()
            }



    }
    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Pilih Jenis Kendaraan")
        val items = arrayOf("Motor", "Mobil")
        val checkedItem = -1

        alertDialog.setSingleChoiceItems(items, checkedItem
        ) { _, which ->
            selectedUnit = items[which]
            selectedUnitText.text = selectedUnit
            binding.editInput.isVisible = true
        }

        alertDialog.setPositiveButton(android.R.string.ok
        ) { dialog, _ ->
            dialog.dismiss()
        }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}

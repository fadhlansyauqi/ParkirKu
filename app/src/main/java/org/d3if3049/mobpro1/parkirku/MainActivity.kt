package org.d3if3049.mobpro1.parkirku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import org.d3if3049.mobpro1.parkirku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var selectedUnitLayout:LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var editInput:EditText
    private lateinit var textResult:TextView
    private lateinit var resultTypeText:TextView
    private lateinit var selectedUnit:String
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        selectedUnitLayout = binding.selectType
        selectedUnitText = binding.textSelect
        editInput = binding.editInput
        textResult = binding.textResult
        resultTypeText = binding.textResultType

        selectedUnit = "Motor"

        selectedUnitLayout.setOnClickListener {
            showAlertDialog()
        }

        editInput.addTextChangedListener{
            val resultText: Int
            val inputVal = editInput.text.toString()

            if (inputVal.isNotEmpty()) {
                val input = inputVal.toInt()
                if(selectedUnit == "Motor"){
                    resultText = input * 2000

                    resultTypeText.text = "Total Biaya Parkir Motor Anda Adalah:"
                }else{
                    resultText = input * 5000

                    resultTypeText.text = "Total Biaya Parkir Mobil Anda Adalah:"
                }

                textResult.text = "Rp $resultText"
            }


        }

    }

    private fun showAlertDialog() {
        val alertDialog:AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Pilih Jenis Kendaraan")
        val items= arrayOf("Motor", "Mobil")
        val checkedItem = -1

        alertDialog.setSingleChoiceItems(items,checkedItem
        ) { _, which ->
            selectedUnit = items[which]
            selectedUnitText.text = selectedUnit
        }

        alertDialog.setPositiveButton(android.R.string.ok
        ) { dialog, _ ->
            dialog.dismiss()
        }

        val alert:AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}
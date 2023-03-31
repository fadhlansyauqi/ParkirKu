package org.d3if3049.mobpro1.parkirku

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    //    Declaring Views
    private lateinit var selectedUnitLayout:LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var editInput:EditText
    private lateinit var textResult:TextView
    private lateinit var resultTypeText:TextView

    //    Input type
    private lateinit var selectedUnit:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Initializing Views
        selectedUnitLayout = findViewById(R.id.selectType)
        selectedUnitText = findViewById(R.id.textSelect)
        editInput = findViewById(R.id.editInput)
        textResult = findViewById(R.id.textResult)
        resultTypeText = findViewById(R.id.textResultType)


//        By Default Fahrenheit is input unit
        selectedUnit = "Motor"

//        Setting alert dialog to appear for selection of input unit
        selectedUnitLayout.setOnClickListener {
            showAlertDialog()
        }

        editInput.addTextChangedListener{
            Log.INFO
            val resultText: Int
            val inputVal = editInput.text.toString()


            if (inputVal.isNotEmpty()) {
                val input = inputVal.toInt() //To convert string to double for calculations
//                Taking Decision As per current input type
                if(selectedUnit == "Motor"){
                    resultText = input * 2000

                    resultTypeText.text = "Total Biaya Parkir Motor Anda Adalah:"
                }else{
                    //(0°C × 9/5) + 32
                    resultText = input * 5000

                    resultTypeText.text = "Total Biaya Parkir Mobil Anda Adalah:"
                }

                textResult.text = "Rp " + resultText.toString()
            }


        }

    }

    private fun showAlertDialog() {
        val alertDialog:AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Pilih Jenis Kendaraan") //setting title of alert dialog
        val items= arrayOf("Motor", "Mobil") //options in alert dialog
        val checkedItem = -1 //no item by default selected

        alertDialog.setSingleChoiceItems(items,checkedItem,
            { _, which ->
                selectedUnit = items[which] //which user has selected
                selectedUnitText.setText(selectedUnit)
            })

        val positiveButton = alertDialog.setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener() { dialog, which ->
                dialog.dismiss()
            })

        val alert:AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}
package org.d3if3049.mobpro1.parkirku.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import org.d3if3049.mobpro1.parkirku.R
import org.d3if3049.mobpro1.parkirku.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var selectedUnitLayout: LinearLayout
    private lateinit var selectedUnitText: TextView
    private lateinit var selectedUnit: String
    private lateinit var binding: FragmentMainBinding
    private var hasil: Int = 0
    private var currentId: Int = 0
    private val viewModel: ViewModelMain by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        selectedUnitLayout = binding.selectType
        selectedUnitText = binding.textSelect
        selectedUnit = ""

        selectedUnitLayout.setOnClickListener {
            showAlertDialog()
        }

        binding.buttonHitungBiaya.setOnClickListener {
            hitungParkir()
        }

        binding.imageViewInfoApp.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_infoFragment)
        }

        binding.imageViewHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
        }

    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        selectedUnitLayout = binding.selectType
//        selectedUnitText = binding.textSelect
//        selectedUnit = ""
//
//        selectedUnitLayout.setOnClickListener {
//            showAlertDialog()
//        }
//
//        binding.buttonHitungBiaya.setOnClickListener {
//            hitungParkir()
//        }
//
//        binding.imageViewInfoApp.setOnClickListener {
//            // ganti intent ke info fragment. jangan ke activity
//            startActivity(
//                Intent(this, InfoActivity::class.java)
//            )
//        }
//
//    }

    private fun hitungParkir() {
        val editInput = binding.editInput.text.toString()

        if (TextUtils.isEmpty(editInput)) {
            Toast.makeText(context, R.string.alertInputKosong, Toast.LENGTH_LONG).show()
            return
        }
        if (editInput.isNotEmpty()) {
            val input = editInput.toInt()

            if (selectedUnit == "Motor") {
                hasil = input * 2000
                binding.textResult.text = "Rp $hasil"
                binding.textResultType.text = "Total Biaya Parkir Motor Anda Adalah:"
            } else {
                hasil = input * 5000
                binding.textResult.text = "Rp $hasil"
                binding.textResultType.text = "Total Biaya Parkir Mobil Anda Adalah:"
            }

//                val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    DateTimeFormatter.ISO_LOCAL_DATE.format(Instant.now())
//                } else {
//                    null
//                }
//
//                val history = History(generateId(), selectedUnit, editInput.toInt(), hasil, date!!, true)
//                viewModel.insertHistory(history)

            Toast.makeText(context, "Biaya Parkir Ditampilkan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateId(): Int {
        return currentId++
    }

    private fun showAlertDialog() {
        //need fix
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Pilih Jenis Kendaraan")
        val items = arrayOf("Motor", "Mobil")
        val checkedItem = -1

        alertDialog.setSingleChoiceItems(
            items, checkedItem
        ) { _, which ->
            selectedUnit = items[which]
            selectedUnitText.text = selectedUnit
            binding.editInput.isVisible = true
        }

        alertDialog.setPositiveButton(
            android.R.string.ok
        ) { dialog, _ ->
            dialog.dismiss()
        }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}

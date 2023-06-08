package org.d3if3049.mobpro1.parkirku.main

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if3049.mobpro1.parkirku.databinding.FragmentMainBinding

class FragmentMain : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonHitungBiaya.setOnClickListener{hitungParkiran()}
    }

    private fun hitungParkiran() {
        val jam = binding.editInput.text.toString()

        if (TextUtils.isEmpty(jam)){
            Toast.makeText(context, "Jam tidak boleh kosong", Toast.LENGTH_LONG).show()
            return
        }
        val selectedValue = binding.selectType.toString()

        viewModel.tampungParkiran(jam.toInt(), selectedValue)
    }
}
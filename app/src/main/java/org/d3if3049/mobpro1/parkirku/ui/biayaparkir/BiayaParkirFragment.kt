package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if3049.ParkirKu.databinding.FragmentBiayaParkirBinding

class BiayaParkirFragment:Fragment() {

    private lateinit var binding: FragmentBiayaParkirBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBiayaParkirBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
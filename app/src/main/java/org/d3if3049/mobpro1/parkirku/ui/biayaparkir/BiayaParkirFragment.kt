package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3049.ParkirKu.databinding.FragmentBiayaParkirBinding
import org.d3if3049.mobpro1.parkirku.model.BiayaParkirObject.getBiayaParkir

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvBiayaParkir) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = BiayaParkirAdapter(getBiayaParkir())
            setHasFixedSize(true)
            Log.d("fragment biaya paprkir", "rv biaya parkir muncul")
        }

    }
}
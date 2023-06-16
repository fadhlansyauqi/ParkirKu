package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3049.ParkirKu.databinding.FragmentBiayaParkirBinding
import org.d3if3049.mobpro1.parkirku.model.BiayaParkirObject.getBiayaParkir
import org.d3if3049.mobpro1.parkirku.network.ApiStatus

class BiayaParkirFragment:Fragment() {

    private val viewModel: BiayaParkirViewModel by lazy {
        ViewModelProvider(this)[BiayaParkirViewModel::class.java]
    }

    private lateinit var binding: FragmentBiayaParkirBinding
    private lateinit var myAdapter: BiayaParkirAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBiayaParkirBinding.inflate(layoutInflater, container, false)
        myAdapter = BiayaParkirAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context,
                RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner){
            myAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner){
            updateProgress(it)
        }

    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCES -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}
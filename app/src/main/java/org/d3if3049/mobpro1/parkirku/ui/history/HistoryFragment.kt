package org.d3if3049.mobpro1.parkirku.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.databinding.FragmentHistoryBinding
import org.d3if3049.mobpro1.parkirku.factory.ViewModelFactory

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: ViewModelHistory by viewModels {
        factory
    }

    private lateinit var myAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        factory = ViewModelFactory(requireActivity().application)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = HistoryAdapter(viewModel)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false)
            this.adapter = myAdapter
            setHasFixedSize(true)
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllHistory().observe(viewLifecycleOwner) { list ->
                if (list == null || list.isEmpty()) {
                    Log.d("HistoryFragment", "List is null or empty")
                    binding.errorText.visibility = View.VISIBLE
                } else {
                    Log.d("HistoryFragment", "List size: ${list.size}")
                    list.forEachIndexed { index, history ->
                        Log.d("HistoryFragment", "Item $index: $history")
                    }
                    myAdapter.submitList(list)
                }
            }
        }
    }
}
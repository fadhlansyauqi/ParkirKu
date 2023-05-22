package org.d3if3049.mobpro1.parkirku.ui.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.R
import org.d3if3049.mobpro1.parkirku.databinding.FragmentHistoryBinding
import org.d3if3049.mobpro1.parkirku.factory.ViewModelFactory
import org.d3if3049.mobpro1.parkirku.manager.DataStoreManager

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: ViewModelHistory by viewModels {
        factory
    }

    private lateinit var myAdapter: HistoryAdapter
    private lateinit var dataStoreManager: DataStoreManager

    companion object {
        private var dataStoreManagerInstance: DataStoreManager? = null

        fun getDataStoreManager(context: Context): DataStoreManager {
            if (dataStoreManagerInstance == null) {
                dataStoreManagerInstance = DataStoreManager(context)
            }
            return dataStoreManagerInstance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        factory = ViewModelFactory(requireActivity().application)
        dataStoreManager = getDataStoreManager(requireContext())
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_history, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                val currentLayoutManager = binding.recyclerView.layoutManager
                if (currentLayoutManager is GridLayoutManager) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                    lifecycleScope.launch {
                        dataStoreManager.saveToDataStore("linear")
                    }
                } else {
                    binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
                    lifecycleScope.launch {
                        dataStoreManager.saveToDataStore("grid")
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = HistoryAdapter(viewModel)
        setHasOptionsMenu(true)

        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false)
            this.adapter = myAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getFromDataStore().collectLatest { prefs ->
                updateLayoutManager(prefs)
            }
        }

        viewModel.getAllHistory().observe(viewLifecycleOwner) { list ->
            if (list == null || list.isEmpty()) {
                Log.d("HistoryFragment", "List is null or empty")
                binding.errorText.visibility = View.VISIBLE
                myAdapter.submitList(list)
            } else {
                Log.d("HistoryFragment", "List size: ${list.size}")
                list.forEachIndexed { index, history ->
                    Log.d("HistoryFragment", "Item $index: $history")
                }
                myAdapter.submitList(list)
            }
        }
    }

    override fun onStop() {
        super.onStop()

        // Simpan preferensi layout ke DataStore
        lifecycleScope.launch {
            dataStoreManager.saveToDataStore(getCurrentLayoutManager())
        }
    }

    fun getCurrentLayoutManager(): String {
        val layoutManager = binding.recyclerView.layoutManager
        return if (layoutManager is GridLayoutManager) {
            "grid"
        } else {
            "linear"
        }
    }

    fun updateLayoutManager(layout: String) {
        binding.recyclerView.apply {
            layoutManager = if (layout == "grid") {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
        }
    }
}
package org.d3if3049.mobpro1.parkirku.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3049.mobpro1.parkirku.databinding.ItemHistoryBinding
import org.d3if3049.mobpro1.parkirku.model.History
import org.d3if3049.mobpro1.parkirku.ui.history.ViewModelHistory

class HistoryAdapter(val viewModel: ViewModelHistory): ListAdapter<History, HistoryAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.apply {
                tanggal.text = history.tanggal.toString()
                biayaParkir.text = history.biayaParkir.toString()
                durasiParkir.text = history.durasiParkir.toString()
                jenisKendaraan.text = history.jenisKendaraan

                buttonDelete.setOnClickListener {
                    if (history.isHistory) {
                        history.isHistory = false
                        viewModel.deleteHistory(history)
                    }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) = with(holder) {
        bind(getItem(position))
    }
}
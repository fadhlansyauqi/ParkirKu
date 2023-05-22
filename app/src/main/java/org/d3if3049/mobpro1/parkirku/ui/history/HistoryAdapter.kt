package org.d3if3049.mobpro1.parkirku.ui.history

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3049.mobpro1.parkirku.R
import org.d3if3049.mobpro1.parkirku.databinding.ItemHistoryBinding
import org.d3if3049.mobpro1.parkirku.model.History
import kotlin.coroutines.coroutineContext

class HistoryAdapter(val viewModel: ViewModelHistory) :
    ListAdapter<History, HistoryAdapter.ViewHolder>(
        DIFF_UTIL
    ) {

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

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.apply {
                tanggal.text = history.tanggal
                biayaParkir.text = history.biayaParkir.toString()
                durasiParkir.text = history.durasiParkir.toString()
                jenisKendaraan.text = history.jenisKendaraan

                buttonDelete.setOnClickListener {
                    if (history.isHistory) {
                        history.isHistory = false
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.deleteHistory(history)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
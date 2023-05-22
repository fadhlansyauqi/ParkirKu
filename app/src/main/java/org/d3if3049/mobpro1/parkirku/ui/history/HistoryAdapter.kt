package org.d3if3049.mobpro1.parkirku.ui.history

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3049.mobpro1.parkirku.databinding.ItemHistoryBinding
import org.d3if3049.mobpro1.parkirku.model.History

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
                return oldItem == newItem && oldItem.isHistory == newItem.isHistory
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
                    val builder = AlertDialog.Builder(it.context)

                    builder.apply {
                        setTitle("Konfirmasi Hapus Data")
                        setMessage("Yakin ingin menghapus histori ini?")
                        setPositiveButton("Iya") { dialog, _ ->
                            if (history.isHistory) {
                                history.isHistory = false
                                viewModel.deleteHistory(history)
                                notifyDataSetChanged()
                            }
                            dialog.dismiss()
                        }
                        setNegativeButton("Batal") { dialog, _ ->
                            dialog.dismiss()
                        }
                    }

                    val dialog = builder.create()
                    dialog.show()
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
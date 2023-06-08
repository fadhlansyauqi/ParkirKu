package org.d3if3049.mobpro1.parkirku.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.d3if3049.mobpro1.parkirku.db.ParkirEntity
import org.d3if3049.mobpro1.parkirku.model.hitungParkiran
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter :
        androidx.recyclerview.widget.ListAdapter<ParkirEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK){
            companion object{
                private val DIFF_CALLBACK =
                    object : DiffUtil.ItemCallback<ParkirEntity>(){
                        override fun areItemsTheSame(
                            oldData: ParkirEntity,
                            newData: ParkirEntity
                        ): Boolean {
                            return oldData.id == newData.id
                        }

                        override fun areContentsTheSame(
                            oldData: ParkirEntity,
                            newData: ParkirEntity
                        ): Boolean {
                            return oldData == newData
                        }
                    }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItemId(position), holder.itemView)
    }

    class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root){
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
        Locale("id", "ID")
        )

        fun bind(item: ParkirEntity, view: View) = with(binding){
            val hasilHitung = item.hitungParkiran()

        }
    }
        }
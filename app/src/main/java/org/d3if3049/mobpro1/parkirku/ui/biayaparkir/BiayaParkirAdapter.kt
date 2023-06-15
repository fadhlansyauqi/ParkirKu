package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3049.ParkirKu.databinding.ItemBiayaParkirBinding
import org.d3if3049.mobpro1.parkirku.model.BiayaParkir

class BiayaParkirAdapter(private val data: List<BiayaParkir>) :
    RecyclerView.Adapter<BiayaParkirAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBiayaParkirBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(biayaParkir: BiayaParkir) = with(binding) {
            lokasiTextView.text = biayaParkir.lokasi
            parkirMotorTextView.text = biayaParkir.parkirmotor
            parkirMobilTextView.text = biayaParkir.parkirmobil
            logoImageView.setImageResource(biayaParkir.gambarId)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBiayaParkirBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

package org.d3if3049.mobpro1.parkirku.ui.biayaparkir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3049.ParkirKu.R
import org.d3if3049.ParkirKu.databinding.ItemBiayaParkirBinding
import org.d3if3049.mobpro1.parkirku.model.BiayaParkir
import org.d3if3049.mobpro1.parkirku.network.BiayaParkirApi

class BiayaParkirAdapter : RecyclerView.Adapter<BiayaParkirAdapter.ViewHolder>() {

    private val data = mutableListOf<BiayaParkir>()
    fun updateData(newaData: List<BiayaParkir>){
        data.clear()
        data.addAll(newaData)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ItemBiayaParkirBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(biayaParkir: BiayaParkir) = with(binding) {
            lokasiTextView.text = biayaParkir.lokasi
            parkirMotorTextView.text = biayaParkir.parkirmotor
            parkirMobilTextView.text = biayaParkir.parkirmobil
            Glide.with(logoImageView.context)
                .load(BiayaParkirApi.getBiayaParkirUrl(biayaParkir.gambarId))
                .error(R.drawable.baseline_broken_image_24)
                .into(logoImageView)

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

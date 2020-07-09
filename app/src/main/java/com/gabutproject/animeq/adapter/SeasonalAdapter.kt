package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.databinding.SeasonalAnimeItemBinding
import com.gabutproject.animeq.network.SeasonalAnimeProperty

class SeasonalClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(propertySeasonal: SeasonalAnimeProperty) = clickListener(propertySeasonal.mal_id)
}

class SeasonalAdapter(private val clickListener: SeasonalClickListener) :
    RecyclerView.Adapter<SeasonalAdapter.ItemViewHolder>() {

    var data = listOf<SeasonalAnimeProperty>()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item, clickListener)
    }

    class ItemViewHolder constructor(private val binding: SeasonalAnimeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            propertySeasonal: SeasonalAnimeProperty,
            clickListener: SeasonalClickListener
        ) {
            binding.property = propertySeasonal
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = SeasonalAnimeItemBinding.inflate(inflater, parent, false)

                return ItemViewHolder(view)
            }
        }
    }
}
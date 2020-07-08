package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.databinding.SeasonalAnimeItemBinding
import com.gabutproject.animeq.network.AnimeProperty

class SeasonalAdapter : RecyclerView.Adapter<SeasonalAdapter.ItemViewHolder>() {

    var data = listOf<AnimeProperty>()
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

        holder.bind(item)
    }

    class ItemViewHolder constructor(private val binding: SeasonalAnimeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(property: AnimeProperty) {
            binding.property = property
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
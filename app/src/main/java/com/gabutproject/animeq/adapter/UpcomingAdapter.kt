package com.gabutproject.animeq.adapter

import com.gabutproject.animeq.network.UpcomingAnimeProperty
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.databinding.CardDateItemBinding

class UpcomingClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(property: UpcomingAnimeProperty) = clickListener(property.mal_id)
}

class UpcomingAdapter constructor(private val clickListener: UpcomingClickListener) :
    RecyclerView.Adapter<UpcomingAdapter.ItemViewHolder>() {

    var data = listOf<UpcomingAnimeProperty>()
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

    class ItemViewHolder constructor(private val binding: CardDateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            property: UpcomingAnimeProperty,
            clickListener: UpcomingClickListener
        ) {
            binding.property = property
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = CardDateItemBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(view)
            }
        }
    }
}
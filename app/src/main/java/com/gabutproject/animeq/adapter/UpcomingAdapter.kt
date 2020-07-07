package com.gabutproject.animeq.adapter

import com.gabutproject.animeq.network.UpcomingAnimeProperty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.R
import com.gabutproject.animeq.util.imageUrl

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.ItemViewHolder>() {

    var data = listOf<UpcomingAnimeProperty>()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(ItemViewHolder.LAYOUT, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.coverImage.imageUrl(item.image_url)
        holder.title.text = item.title
    }

    class ItemViewHolder constructor(binding: View) :
        RecyclerView.ViewHolder(binding) {

        val title: TextView = binding.findViewById(R.id.item_title)
        val coverImage: ImageView = binding.findViewById(R.id.cover_image)

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.seasonal_anime_item
        }
    }
}
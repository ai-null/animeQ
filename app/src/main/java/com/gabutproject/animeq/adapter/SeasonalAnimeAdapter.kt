package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.R
// import com.gabutproject.animeq.databinding.SeasonalAnimeItemBinding
import com.gabutproject.animeq.network.AnimeProperty
import com.gabutproject.animeq.util.imageUrl

class ItemViewHolder(private var binding: View) :
    RecyclerView.ViewHolder(binding) {

    val title: TextView = binding.findViewById(R.id.item_title)
    val coverImage: ImageView = binding.findViewById(R.id.cover_image)

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.seasonal_anime_item
    }
}

class SeasonalAnimeAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    var data = listOf<AnimeProperty>()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // val view = SeasonalAnimeItemBinding.inflate(layoutInflater)
        val view = layoutInflater.inflate(R.layout.seasonal_anime_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        // holder.bind(item)
        holder.coverImage.imageUrl(item.image_url)
        holder.title.text = item.title
    }

}
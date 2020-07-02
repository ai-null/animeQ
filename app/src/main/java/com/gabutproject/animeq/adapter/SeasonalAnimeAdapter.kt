package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.animeq.R
import com.gabutproject.animeq.network.AnimeProperty

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.item_title)

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
        val view = layoutInflater.inflate(ItemViewHolder.LAYOUT, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.title.text = item.title
    }

}
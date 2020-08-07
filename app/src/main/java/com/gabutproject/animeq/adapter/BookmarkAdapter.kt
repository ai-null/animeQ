package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.gabutproject.animeq.databinding.BookmarkItemBinding
import com.gabutproject.animeq.network.Bookmark

class BookmarkAdapter : BaseAdapter() {
    var data = listOf<Bookmark>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Any = data[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = data.size
    override fun getView(position: Int, viewHolder: View?, container: ViewGroup): View {
        val holder = from(container)

        holder.bind(data[position])
        return holder.binding.root
    }

    class ItemViewHolder(val binding: BookmarkItemBinding) {
        fun bind(
            bookmark: Bookmark
        ) {
            binding.property = bookmark
        }
    }

    companion object {
        fun from(container: ViewGroup): ItemViewHolder {
            val inflater = LayoutInflater.from(container.context)
            val view = BookmarkItemBinding.inflate(inflater, container, false)

            return ItemViewHolder(view)
        }
    }
}
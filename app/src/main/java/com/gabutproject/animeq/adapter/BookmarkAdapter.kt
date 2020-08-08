package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.gabutproject.animeq.databinding.BookmarkItemBinding
import com.gabutproject.animeq.network.Bookmark

class BookmarkClickListener(val clickListener: (mal_id: Int) -> Unit) {
    fun onClick(bookmark: Bookmark) = clickListener(bookmark.mal_id)
}

class BookmarkAdapter constructor(val clickListener: BookmarkClickListener) : BaseAdapter() {
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

        holder.bind(data[position], clickListener)
        return holder.binding.root
    }

    class ItemViewHolder(val binding: BookmarkItemBinding) {
        fun bind(
            bookmark: Bookmark,
            clickListener: BookmarkClickListener
        ) {
            binding.property = bookmark
            binding.clickListener = clickListener
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
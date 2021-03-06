package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.gabutproject.animeq.databinding.ResultItemBinding
import com.gabutproject.animeq.network.Result

class ResultClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(property: Result) = clickListener(property.mal_id)
}

class ResultAdapter(private val clickListener: ResultClickListener) : BaseAdapter() {

    var data = listOf<Result>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int = data.size

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val holder = ItemViewHolder.from(parent)

        holder.bind(data[position], clickListener)
        return holder.binding.root
    }

    class ItemViewHolder(val binding: ResultItemBinding) {
        fun bind(
            property: Result,
            clickListener: ResultClickListener
        ) {
            binding.property = property
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = ResultItemBinding.inflate(inflater, parent, false)

                return ItemViewHolder(view)
            }
        }
    }
}
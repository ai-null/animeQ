package com.gabutproject.animeq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.gabutproject.animeq.databinding.ResultItemBinding
import com.gabutproject.animeq.network.Result

class ResultAdapter : BaseAdapter() {

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

        holder.bind(data[position])
        return holder.binding.root
    }

    class ItemViewHolder(val binding: ResultItemBinding) {
        fun bind(property: Result) {
            binding.property = property
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
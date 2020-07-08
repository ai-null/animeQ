package com.gabutproject.animeq.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gabutproject.animeq.R

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(this.context)
            .load(imageUri)
            .into(this)
    }
}

@BindingAdapter("startDate")
fun TextView.startDate(date: String?) {
    text = date ?: context.getString(R.string.to_be_announced)
}
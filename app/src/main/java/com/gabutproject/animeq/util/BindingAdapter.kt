package com.gabutproject.animeq.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gabutproject.animeq.R

/**
 * Image processing using glide
 * TODO: implement broken image & loading image
 */
@BindingAdapter("imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(this.context)
            .load(imageUri)
            .into(this)
    }
}

/**
 * used for card with date or ui that implement date
 * if the anime date was confirmed, return actual date
 * if not, return TBA or To Be Announced
 */
@BindingAdapter("startDate")
fun TextView.startDate(date: String?) {
    text = date ?: context.getString(R.string.to_be_announced)
}
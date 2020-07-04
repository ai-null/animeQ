package com.gabutproject.animeq.util

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide

fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(this.context)
            .load(imageUri)
            .into(this)
    }
}
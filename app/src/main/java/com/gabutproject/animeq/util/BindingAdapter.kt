package com.gabutproject.animeq.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gabutproject.animeq.R

/**
 * Image processing using glide
 *
 * @param imageUrl String?
 *
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
 *
 * @param date String?
 *
 * if the anime date was confirmed, return actual date
 * if not, return TBA or To Be Announced
 */
@BindingAdapter("startDate")
fun TextView.startDate(date: String?) {
    text = date ?: context.getString(R.string.to_be_announced)
}

/**
 * used for detail activity.
 *
 * @param status Boolean
 *  airing status
 *
 * the data fetched from server returns Boolean value.
 * so in order to make readable information, this will change the value to:
 * Aired == true / TBA
 */
@BindingAdapter("status")
fun TextView.status(status: Boolean) {
    text = if (status) {
        context.getString(R.string.aired)
    } else {
        context.getString(R.string.to_be_announced)
    }
}

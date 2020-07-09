package com.gabutproject.animeq.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gabutproject.animeq.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val headline: TextView = this.findViewById(R.id.headline)

        if (intent.hasExtra("mal_id")) {
            headline.text = intent.extras?.get("mal_id").toString()
        }
    }
}
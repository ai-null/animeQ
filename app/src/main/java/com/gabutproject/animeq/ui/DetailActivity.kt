package com.gabutproject.animeq.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val headline: TextView = this.findViewById(R.id.headline)

        if (intent.hasExtra("mal_id")) {
            val data: Int = intent.extras!!.getInt("mal_id")

            viewModel = DetailViewModel(data)
        }

        viewModel.animeProperty.observe(this, Observer {
            it?.let {
                headline.text = it.title
            }
        })
    }
}
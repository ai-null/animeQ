package com.gabutproject.animeq.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabutproject.animeq.R
import com.gabutproject.animeq.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel = SearchViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
    }
}
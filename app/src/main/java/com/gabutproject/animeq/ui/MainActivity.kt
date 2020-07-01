package com.gabutproject.animeq.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gabutproject.animeq.R
import com.gabutproject.animeq.adapter.SeasonalAnimeAdapter
import com.gabutproject.animeq.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainActivityViewModel()

    private val adapter = SeasonalAnimeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the activity to use the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // define viewModel and lifecycleOwner to observe changed data
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.seasonalAnimeList.layoutManager = GridLayoutManager(this, 2)
        binding.seasonalAnimeList.adapter = adapter

        updateLiveData()
    }

    private fun updateLiveData() {
        viewModel.seasonalAnime.observe(this, Observer {
            it?.let {
                adapter.data = it.anime
            }
        })
    }
}
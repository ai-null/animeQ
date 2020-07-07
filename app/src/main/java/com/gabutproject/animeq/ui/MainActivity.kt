package com.gabutproject.animeq.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabutproject.animeq.R
import com.gabutproject.animeq.adapter.SeasonalAdapter
import com.gabutproject.animeq.adapter.UpcomingAdapter
import com.gabutproject.animeq.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainActivityViewModel()

    private val seasonalAdapter = SeasonalAdapter()
    private val upcomingAdapter = UpcomingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the activity to use the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // define viewModel and lifecycleOwner to observe changed data
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val seasonalManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // seasonal listView
        binding.seasonalAnimeList.layoutManager = seasonalManager
        binding.seasonalAnimeList.adapter = seasonalAdapter

        val upcomingManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // upcoming listView
        binding.upcomingAnimeList.layoutManager = upcomingManager
        binding.upcomingAnimeList.adapter = upcomingAdapter

        updateLiveData()
    }

    private fun updateLiveData() {
        viewModel.seasonalAnime.observe(this, Observer {
            it?.let {
                seasonalAdapter.data = it.anime
            }
        })

        viewModel.upcomingAnime.observe(this, Observer {
            it?.let {
                upcomingAdapter.data = it.top
            }
        })
    }
}
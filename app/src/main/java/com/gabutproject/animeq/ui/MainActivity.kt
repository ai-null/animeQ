package com.gabutproject.animeq.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabutproject.animeq.R
import com.gabutproject.animeq.adapter.SeasonalAdapter
import com.gabutproject.animeq.adapter.SeasonalClickListener
import com.gabutproject.animeq.adapter.UpcomingAdapter
import com.gabutproject.animeq.databinding.ActivityMainBinding
import com.gabutproject.animeq.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainActivityViewModel()

    private val seasonalAdapter = SeasonalAdapter(SeasonalClickListener { id ->
        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
    })
    private lateinit var upcomingAdapter: UpcomingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the activity to use the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // define viewModel and lifecycleOwner to observe changed data
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // calling heavy components
        initSeasonalList()
        initUpcomingList()

        // live data handler
        updateLiveData()
    }

    private fun initSeasonalList() {
        val seasonalManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // seasonal listView
        binding.seasonalAnimeList.layoutManager = seasonalManager
        binding.seasonalAnimeList.adapter = seasonalAdapter
    }

    private fun initUpcomingList() {
        // define adapter
        val upcomingManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // add application context to get String Resource
        val application = requireNotNull(this.application)
        upcomingAdapter = UpcomingAdapter(application)

        // upcoming listView
        binding.upcomingAnimeList.layoutManager = upcomingManager
        binding.upcomingAnimeList.adapter = upcomingAdapter
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
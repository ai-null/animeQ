package com.gabutproject.animeq.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabutproject.animeq.adapter.SeasonalAdapter
import com.gabutproject.animeq.adapter.SeasonalClickListener
import com.gabutproject.animeq.adapter.UpcomingAdapter
import com.gabutproject.animeq.adapter.UpcomingClickListener
import com.gabutproject.animeq.databinding.MainFragmentBinding
import com.gabutproject.animeq.ui.activity.DetailActivity
import com.gabutproject.animeq.viewmodel.MainViewModel

class MainFragment : Fragment() {

    // TODO: cleanup this activity
    private lateinit var binding: MainFragmentBinding
    private val viewModel = MainViewModel()

    private lateinit var upcomingAdapter: UpcomingAdapter
    private lateinit var seasonalAdapter: SeasonalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        // define viewModel and lifecycleOwner to observe changed data
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // calling heavy components
        initSeasonalList()
        initUpcomingList()

        // live data handler
        updateLiveData()

        return binding.root
    }

    /**
     * Init seasonal anime list
     * TODO: put all initial data fetching to loading screen
     */
    private fun initSeasonalList() {
        val seasonalManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // add adapter to the list
        seasonalAdapter = SeasonalAdapter(SeasonalClickListener { id ->
            viewModel.onNavigateToDetail(id)
        })

        // seasonal listView
        binding.seasonalAnimeList.layoutManager = seasonalManager
        binding.seasonalAnimeList.adapter = seasonalAdapter
    }

    /**
     * init upcoming anime list
     */
    private fun initUpcomingList() {
        val upcomingManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // TODO: refactor and find why it's must be lateinit
        upcomingAdapter = UpcomingAdapter(UpcomingClickListener { id ->
            viewModel.onNavigateToDetail(id)
        })

        // upcoming listView
        binding.upcomingAnimeList.layoutManager = upcomingManager
        binding.upcomingAnimeList.adapter = upcomingAdapter
    }

    /**
     * live data handler
     * all setter observer must be put here
     */
    private fun updateLiveData() {
        viewModel.seasonalAnime.observe(viewLifecycleOwner, Observer {
            it?.let {
                seasonalAdapter.data = it.anime
            }
        })

        viewModel.upcomingAnime.observe(viewLifecycleOwner, Observer {
            it?.let {
                upcomingAdapter.data = it.top
            }
        })

        // navigate to detail and pass mal_id to fetch on the detail screen
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { id ->
            id?.let {
                startActivity(
                    Intent(
                        this.context,
                        DetailActivity::class.java
                    ).putExtra("mal_id", id)
                )

                viewModel.navigateComplete()
            }
        })

        viewModel.result.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                Toast.makeText(this.context, data.results[0].title, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
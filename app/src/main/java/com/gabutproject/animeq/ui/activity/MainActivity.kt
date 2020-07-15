package com.gabutproject.animeq.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabutproject.animeq.R
import com.gabutproject.animeq.adapter.SeasonalAdapter
import com.gabutproject.animeq.adapter.SeasonalClickListener
import com.gabutproject.animeq.adapter.UpcomingAdapter
import com.gabutproject.animeq.adapter.UpcomingClickListener
import com.gabutproject.animeq.databinding.ActivityMainBinding
import com.gabutproject.animeq.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    // TODO: cleanup this activity
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    private lateinit var upcomingAdapter: UpcomingAdapter
    private lateinit var seasonalAdapter: SeasonalAdapter

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

    /**
     * Init seasonal anime list
     * TODO: put all initial data fetching to loading screen
     */
    private fun initSeasonalList() {
        val seasonalManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

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
            this,
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

        // navigate to detail and pass mal_id to fetch on the detail screen
        viewModel.navigateToDetail.observe(this, Observer { id ->
            id?.let {
                startActivity(
                    Intent(
                        this,
                        DetailActivity::class.java
                    ).putExtra("mal_id", id)
                )

                viewModel.navigateComplete()
            }
        })

        viewModel.result.observe(this, Observer { data ->
            data?.let {
                Toast.makeText(this, data.results[0].title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)

        val searchView = menu!!.findItem(R.id.search_item).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) viewModel.search(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.search -> {
//                startActivity(Intent(this, SearchActivity::class.java))
//                true
//            }
//            else -> true
//        }
//    }
}
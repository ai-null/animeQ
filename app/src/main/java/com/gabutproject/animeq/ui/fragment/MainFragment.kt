package com.gabutproject.animeq.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabutproject.animeq.R
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

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

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
    }

    /**
     * options bar creations
     *
     * for this screen it only handle search method
     * like accepting query from user, and let the viewModel handle the rest
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // inflate menu layout to this fragment
        inflater.inflate(R.menu.main_search_item, menu)
        super.onCreateOptionsMenu(menu, inflater)

        // define search view component
        val searchView = menu.findItem(R.id.main_search_item).actionView as SearchView

        // set methods to search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // this will executed after user submitted
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToSearchFragment(query)
                    )
                }

                return true
            }

            // this will executed when user typing
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
}
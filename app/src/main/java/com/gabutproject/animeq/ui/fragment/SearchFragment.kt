package com.gabutproject.animeq.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.adapter.ResultAdapter
import com.gabutproject.animeq.adapter.ResultClickListener
import com.gabutproject.animeq.databinding.SearchFragmentBinding
import com.gabutproject.animeq.ui.activity.DetailActivity
import com.gabutproject.animeq.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel = SearchViewModel()
    private lateinit var adapter: ResultAdapter
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        // get data from query
        val query = SearchFragmentArgs.fromBundle(requireArguments()).query

        // enable action bar & set title
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = query

        // setup all binding related
        setupBinding()
        search(query)

        // live data observer
        updateLiveData()

        return binding.root
    }

    private fun setupBinding() {
        adapter = ResultAdapter(ResultClickListener { id ->
            viewModel.onNavigateToDetail(id)
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // look up the component and define the adapter with ResultAdapter
        binding.resultList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_item, menu)

        // define search view component
        val item = menu.findItem(R.id.search_item)
        val searchView = item.actionView as SearchView

        // set methods to search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // this will executed after user submitted
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    search(query)
                }

                return true
            }

            // this will executed when user typing
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    /**
     * Search by title
     * it's better to use local method
     * instead of defining viewModel on each call
     *
     * @param query String
     */
    private fun search(query: String) {
        viewModel.search(query)
    }

    /**
     * LiveData observer,
     * all live-data related must to put inside here
     */
    private fun updateLiveData() {
        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                adapter.data = result.results
            }
        })

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

        viewModel.query.observe(viewLifecycleOwner, Observer { query ->
            query?.let {
                (activity as AppCompatActivity).supportActionBar?.title = query
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package com.gabutproject.animeq.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.adapter.ResultAdapter
import com.gabutproject.animeq.databinding.SearchFragmentBinding
import com.gabutproject.animeq.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel = SearchViewModel()
    private val adapter = ResultAdapter()
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        val query = SearchFragmentArgs.fromBundle(requireArguments()).query
        search(query)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // look up the component and define the adapter with ResultAdapter
        binding.resultList.adapter = adapter

        // live data observer
        updateLiveData()

        return binding.root
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
    }
}
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
import com.gabutproject.animeq.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel = SearchViewModel()
    private val adapter = ResultAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val query = SearchFragmentArgs.fromBundle(requireArguments()).query
        search(query)
        updateLiveData()

        val view = inflater.inflate(R.layout.search_fragment, container, false)
        view.findViewById<GridView>(R.id.result_list).adapter = adapter

        return view
    }

    private fun search(query: String) {
        viewModel.search(query)
    }

    private fun updateLiveData() {
        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                adapter.data = result.results
            }
        })
    }
}
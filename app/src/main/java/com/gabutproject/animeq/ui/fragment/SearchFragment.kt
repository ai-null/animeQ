package com.gabutproject.animeq.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel = SearchViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val query = SearchFragmentArgs.fromBundle(requireArguments()).query
        search(query)

        updateLiveData()

        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    private fun search(query: String) {
        viewModel.search(query)
    }

    private fun updateLiveData() {
        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                Toast.makeText(this.context, result.results[0].title, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
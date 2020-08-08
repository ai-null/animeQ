package com.gabutproject.animeq.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabutproject.animeq.adapter.BookmarkAdapter
import com.gabutproject.animeq.adapter.BookmarkClickListener
import com.gabutproject.animeq.databinding.BookmarkFragmentBinding
import com.gabutproject.animeq.ui.activity.DetailActivity
import com.gabutproject.animeq.viewmodel.BookmarkViewModel

class BookmarkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val viewModel = BookmarkViewModel(application)

        val binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        val adapter = BookmarkAdapter(BookmarkClickListener { mal_id ->
            viewModel.navigateToDetail(mal_id)
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.bookmarkList.adapter = adapter

        viewModel.bookmarks.observe(viewLifecycleOwner, Observer { bookmarkList ->
            bookmarkList?.let {
                adapter.data = it
            }
        })

        viewModel.onNavigateToDetail.observe(viewLifecycleOwner, Observer { mal_id ->
            mal_id?.let {
                startActivity(
                    Intent(
                        this.context,
                        DetailActivity::class.java
                    ).putExtra("mal_id", it)
                )
            }
        })

        return binding.root
    }
}
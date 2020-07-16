package com.gabutproject.animeq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.gabutproject.animeq.R
import com.gabutproject.animeq.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)

        // define search view component
        val searchView = menu!!.findItem(R.id.search_item).actionView as SearchView

        // set methods to search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // this will executed after user submitted
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) viewModel.search(query)

                return true
            }

            // this will executed when user typing
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }
}
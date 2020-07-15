package com.gabutproject.animeq.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.databinding.DetailActivityBinding
import com.gabutproject.animeq.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set content view
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)

        // set lifeCycleOwner so the activity can observe data change
        binding.lifecycleOwner = this

        // null-safety - check if the data is ready or not
        // mal_id should be ready the moment detail activity accessed. this is for safety
        if (intent.hasExtra("mal_id")) {
            viewModel = DetailViewModel(intent.extras!!.getInt("mal_id"))

            updateLiveData()
        }
    }

    private fun updateLiveData() {
        viewModel.animeProperty.observe(this, Observer {
            it?.let {
                binding.property = it
                title = it.title
            }
        })
    }
}
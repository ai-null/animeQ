package com.gabutproject.animeq.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.gabutproject.animeq.R
import com.gabutproject.animeq.databinding.DetailActivityBinding
import com.gabutproject.animeq.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    private lateinit var binding: DetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.lifecycleOwner = this


        if (intent.hasExtra("mal_id")) {
            val data: Int = intent.extras!!.getInt("mal_id")

            viewModel = DetailViewModel(data)
            updateLiveData()
        }
    }

    private fun updateLiveData() {
        viewModel.animeProperty.observe(this, Observer {
            it?.let {
                binding.property = it
            }
        })
    }
}
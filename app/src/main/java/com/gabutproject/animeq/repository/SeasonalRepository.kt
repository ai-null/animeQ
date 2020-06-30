package com.gabutproject.animeq.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabutproject.animeq.network.JikanNetwork
import com.gabutproject.animeq.network.SeasonalProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This repository used to make methods to call to the server using JikanService,
 * and it can be called from any viewModels.
 *
 * separating repository and viewModel makes it easy to finds bugs,
 * so it will be a good practice to make readable code
 */
class SeasonalRepository {

    // getter property of seasonalAnime
    lateinit var seasonalAnime: SeasonalProperty

    /**
     * Refresh data from the network and replace the old data
     * it can be used for initial call and refresh for anytime
     */
    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val data = JikanNetwork.service.getCurrentSeasonAsync()
            seasonalAnime = data
        }
    }
}
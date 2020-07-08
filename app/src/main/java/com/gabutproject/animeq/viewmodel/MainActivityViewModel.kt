package com.gabutproject.animeq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.SeasonalProperty
import com.gabutproject.animeq.network.UpcomingProperty
import com.gabutproject.animeq.repository.JikanRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {
    // used for make an http request to seasonal-anime-related
    private val jikanRepository = JikanRepository()

    // setup supervisorJob to cancel all thread-related methods in once
    private val job = SupervisorJob()

    // uiScope to run method on main-thread, don't make a call inside main thread
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _seasonalAnime = MutableLiveData<SeasonalProperty>()
    val seasonalAnime: LiveData<SeasonalProperty> get() = _seasonalAnime

    private val _upcomingAnime = MutableLiveData<UpcomingProperty>()
    val upcomingAnime: LiveData<UpcomingProperty> get() = _upcomingAnime

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        uiScope.launch {
            jikanRepository.refreshData()

            _seasonalAnime.value = jikanRepository.seasonalAnime
            _upcomingAnime.value = jikanRepository.upcomingAnime
        }
    }

    private val _navigateToDetail = MutableLiveData<Int>()
    val navigateToDetail: LiveData<Int> get() = _navigateToDetail

    fun onItemClick(id: Int) {
        _navigateToDetail.value = id
    }

    fun navigateComplete() {
        _navigateToDetail.value = null
    }
}

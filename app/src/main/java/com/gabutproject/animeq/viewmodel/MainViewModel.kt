package com.gabutproject.animeq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.SeasonalProperty
import com.gabutproject.animeq.network.UpcomingProperty
import com.gabutproject.animeq.repository.JikanRepository
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    // used for make an http request to seasonal-anime-related
    private val jikanRepository = JikanRepository()

    // setup supervisorJob to cancel all thread-related methods in once
    private val job = SupervisorJob()

    // uiScope to run method on main-thread, don't make a call inside main thread
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    // put setter and getter below
    // SETTER
    private val _seasonalAnime = MutableLiveData<SeasonalProperty>()
    private val _upcomingAnime = MutableLiveData<UpcomingProperty>()
    private val _navigateToDetail = MutableLiveData<Int>()
    private val _isLoading = MutableLiveData<Boolean>()

    // GETTER
    val seasonalAnime: LiveData<SeasonalProperty> get() = _seasonalAnime
    val upcomingAnime: LiveData<UpcomingProperty> get() = _upcomingAnime
    val navigateToDetail: LiveData<Int> get() = _navigateToDetail
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        refreshDataFromRepository()
    }

    /**
     * get & refresh data, used for initial call or pull-to-refresh
     * to get the new data.
     * TODO: implement pull-to-refresh
     */
    private fun refreshDataFromRepository() {
        uiScope.launch {
            _isLoading.value = true
            jikanRepository.refreshData()

            _seasonalAnime.value = jikanRepository.seasonalAnime
            _upcomingAnime.value = jikanRepository.upcomingAnime
            _isLoading.value = false
        }
    }

    fun onNavigateToDetail(id: Int) {
        _navigateToDetail.value = id
    }

    fun navigateComplete() {
        _navigateToDetail.value = null
    }
}

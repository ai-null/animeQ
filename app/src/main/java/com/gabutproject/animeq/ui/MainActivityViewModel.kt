package com.gabutproject.animeq.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.SeasonalProperty
import com.gabutproject.animeq.repository.SeasonalRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    private val seasonalRepository = SeasonalRepository()

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _seasonalAnime = MutableLiveData<SeasonalProperty>()
    val seasonalAnime: LiveData<SeasonalProperty> get() = _seasonalAnime

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        uiScope.launch {
            seasonalRepository.refreshData()

            _seasonalAnime.value = seasonalRepository.seasonalAnime
        }
    }
}

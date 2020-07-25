package com.gabutproject.animeq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.AnimeProperty
import com.gabutproject.animeq.repository.JikanRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val mal_id: Int) : ViewModel() {
    // make job to handle all job-related, such as canceling all job
    private val job = SupervisorJob()

    // uiScope handler
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    // repository
    private val jikanRepository = JikanRepository()

    // contains detailed information of anime
    private val _animeProperty = MutableLiveData<AnimeProperty>()
    val animeProperty: LiveData<AnimeProperty> get() = _animeProperty

    // Error state
    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> get() = _error

    init {
        getData()
    }

    /**
     * Get Detail information from server, handled by repository
     * used for initial call, since detail page don't need pull-to-refresh
     * it will call this once from init
     */
    private fun getData() {
        uiScope.launch {
            try {
                _animeProperty.value = jikanRepository.getAnimeDetail(mal_id)
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}
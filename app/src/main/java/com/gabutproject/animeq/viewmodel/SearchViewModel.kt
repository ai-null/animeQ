package com.gabutproject.animeq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.SearchProperty
import com.gabutproject.animeq.repository.JikanRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    // used for make an http request to seasonal-anime-related
    private val jikanRepository = JikanRepository()

    // setup supervisorJob to cancel all thread-related methods in once
    private val job = SupervisorJob()

    // uiScope to run method on main-thread, don't make a call inside main thread
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    // getter and setter live data
    // SETTER
    private val _result = MutableLiveData<SearchProperty>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _navigateToDetail = MutableLiveData<Int>()
    private val _query = MutableLiveData<String>()
    private val _error = MutableLiveData<Exception>()

    // GETTER
    val result: LiveData<SearchProperty> get() = _result
    val isLoading: LiveData<Boolean> get() = _isLoading
    val navigateToDetail: LiveData<Int> get() = _navigateToDetail
    val query: LiveData<String> get() = _query
    val error: LiveData<Exception> get() = _error


    /**
     * search anime by title
     *
     * @param keyword String
     *  title you want to look up. the search only works on 3+ letters
     */
    fun search(keyword: String = "") {
        uiScope.launch {
            // begin the search
            _query.value = keyword
            _isLoading.value = true

            try {
                _result.value = jikanRepository.search(keyword)
            } catch (e: Exception) {
                _error.value = e
            }

            // update loading state
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
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

class SearchViewModel: ViewModel() {
    // used for make an http request to seasonal-anime-related
    private val jikanRepository = JikanRepository()

    // setup supervisorJob to cancel all thread-related methods in once
    private val job = SupervisorJob()

    // uiScope to run method on main-thread, don't make a call inside main thread
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _result = MutableLiveData<SearchProperty>()
    val result: LiveData<SearchProperty> get() = _result

    /**
     * search anime by title
     *
     * @param keyword String
     *  title you want to look up. the search only works on 3+ letters
     */
    fun search(keyword: String = "") {
        uiScope.launch {
            jikanRepository.search(keyword)

            _result.value = jikanRepository.searchResult
        }
    }
}
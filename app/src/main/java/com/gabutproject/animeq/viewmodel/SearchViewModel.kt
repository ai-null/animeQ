package com.gabutproject.animeq.viewmodel

import android.util.Log
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

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val repository = JikanRepository()

    private val _result = MutableLiveData<SearchProperty>()
    val result: LiveData<SearchProperty> get() = _result

    init {
        search()
    }

    private fun search() {
        uiScope.launch {
            repository.search("Fate")

            _result.value = repository.searchResult
            Log.i("search_result", repository.searchResult.results[0].title)
        }
    }
}
package com.gabutproject.animeq.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.Bookmark
import com.gabutproject.animeq.repository.AnimeQRepository
import kotlinx.coroutines.*

class BookmarkViewModel (app: Application): ViewModel() {
    private val animeQRepository = AnimeQRepository(app)

    private val job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val _bookmarks = MutableLiveData<List<Bookmark>>()
    val bookmarks: LiveData<List<Bookmark>> get() = _bookmarks

    private val _onNavigateToDetail = MutableLiveData<Int>()
    val onNavigateToDetail: LiveData<Int> get() = _onNavigateToDetail


    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        uiScope.launch {
            val bookmarkList = animeQRepository.getBookmarks()

            _bookmarks.value = bookmarkList
        }
    }

    fun navigateToDetail(mal_id: Int) {
        _onNavigateToDetail.value = mal_id
    }

    fun onNavigateCompleted() {
        _onNavigateToDetail.value = null
    }
}
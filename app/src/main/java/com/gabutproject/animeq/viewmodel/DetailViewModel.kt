package com.gabutproject.animeq.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.AnimeProperty
import com.gabutproject.animeq.repository.AnimeQRepository
import com.gabutproject.animeq.repository.JikanRepository
import kotlinx.coroutines.*

class DetailViewModel(private val mal_id: Int, app: Application) : ViewModel() {

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

    private val animeQRepository = AnimeQRepository(app)

    init {
        checkBookmarkedFromRepository()
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

    /**
     * Bookmarked LiveData
     *  set bookmark of specific information
     */
    private val _bookmarked = MutableLiveData<Boolean>(false)
    val bookmarked: LiveData<Boolean> get() = _bookmarked

    /**
     * this method used for checking bookmarked status from db
     * and will executed the moment viewModel initialized
     */
    private fun checkBookmarkedFromRepository() {
        uiScope.launch {
            val data = animeQRepository.checkBookmark(mal_id)
            _bookmarked.value = data.isNotEmpty()
        }
    }

    private val _bookmarkedStatus = MutableLiveData<Boolean>()
    val bookmarkedStatus: LiveData<Boolean> get() = _bookmarkedStatus

    /**
     * Add bookmark to the database
     * the repository using IO thread so it will not block the UI
     * then executed using UI thread
     */
    fun bookmark() {
        uiScope.launch {
            _bookmarked.value?.let {
                try {
                    if (it) animeQRepository.removeBookmark(mal_id)
                    else {
                        _animeProperty.value?.let { data ->
                            animeQRepository.addBookmark(mal_id, data.title, data.image_url)
                        }
                    }

                    _bookmarked.value = !it
                    _bookmarkedStatus.value = !it
                } catch (err: Exception) {
                    _error.value = err
                }
            }
        }
    }
}
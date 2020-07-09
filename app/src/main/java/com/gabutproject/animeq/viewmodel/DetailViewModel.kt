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
    private val job = SupervisorJob()

    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val jikanRepository = JikanRepository()

    private val _animeProperty = MutableLiveData<AnimeProperty>()
    val animeProperty: LiveData<AnimeProperty> get() = _animeProperty

    init {
        uiScope.launch {
            jikanRepository.getAnimeDetail(mal_id)

            _animeProperty.value = jikanRepository.anime
        }
    }
}
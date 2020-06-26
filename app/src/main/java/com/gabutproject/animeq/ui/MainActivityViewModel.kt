package com.gabutproject.animeq.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.JikanNetwork
import com.gabutproject.animeq.network.JikanService
import com.gabutproject.animeq.network.SeasonalProperty
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {
    val service = JikanNetwork.service

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        uiScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val data = service.getCurrentSeason().await()

            Log.i("anime data", data.toString())
        }
    }
}

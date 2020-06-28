package com.gabutproject.animeq.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gabutproject.animeq.network.JikanNetwork
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        getData()
    }

    private fun getData() {
        uiScope.launch {
            val data = JikanNetwork.service.getCurrentSeasonAsync()

            Log.i("anime data", data.anime[0].title)
        }
    }
}

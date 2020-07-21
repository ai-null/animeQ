package com.gabutproject.animeq.repository

import com.gabutproject.animeq.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This repository used to make methods to call to the server using JikanService,
 * and it can be called from any viewModels.
 *
 * separating repository and viewModel makes it easy to finds bugs,
 * so it will be a good practice to make readable code
 */
class JikanRepository {

    lateinit var seasonalAnime: SeasonalProperty
    lateinit var upcomingAnime: UpcomingProperty
    lateinit var anime: AnimeProperty
    lateinit var searchResult: SearchProperty

    /**
     * Refresh data from the network and replace the old data
     * it can be used for initial call and refresh for anytime
     */
    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val seasonalData = JikanNetwork.service.getCurrentSeason()
            val upcomingData = JikanNetwork.service.getTopUpcoming()

            seasonalAnime = seasonalData
            upcomingAnime = upcomingData
        }
    }

    /**
     * Get detailed information of provided animeId.
     * use this method on detail page only
     *
     * @param mal_id Int
     */
    suspend fun getAnimeDetail(mal_id: Int) {
        withContext(Dispatchers.IO) {
            val animeDetail = JikanNetwork.service.getDetailAnime(mal_id)

            anime = animeDetail
        }
    }

    /**
     * Search for title
     *
     * @param key String
     */
    suspend fun search(key: String) {
        withContext(Dispatchers.IO) {
            val result = JikanNetwork.service.search(key)

            searchResult = result
        }
    }
}
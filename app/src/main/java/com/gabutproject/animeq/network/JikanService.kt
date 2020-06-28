package com.gabutproject.animeq.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL: String = "https://api.jikan.moe/v3/"

/**
 * @params /:year/:season - ex: /2011/summer
 *  return list of data of following season & year
 *
 * @param /later - ex: /season/later
 *  return the next season's anime list
 */
const val SEASON: String = "season"

/**
 * @param /anime - ex: /search/anime
 *  Search following data by filtered by anime/manga list
 *
 * @query q - ex: q=Fate/Zero
 * @query page - ex: page=1
 *  - return list of anime with similar title
 */
const val SEARCH: String = "search"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface JikanService {
    @GET(SEASON)
    suspend fun getCurrentSeasonAsync(): SeasonalProperty
}

object JikanNetwork {
    val service: JikanService by lazy {
        retrofit.create(JikanService::class.java)
    }
}


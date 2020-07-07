package com.gabutproject.animeq.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

/**
 * @param /anime/{page}
 *  return top anime by page
 * @param /anime/1/airing - get top airing anime
 * @param /anime/1/movie - get top movie anime
 * @param /anime/1/tv - get top tv
 * @param /anime/1/special - get top special
 * @param /anime/1/upcoming - get top upcoming anime
 * @param /anime/1/ova - get top ova anime
 */
const val TOP: String = "top"

// add moshi to convert kotlin adapter into java object
// since data class not working so well with bare retrofit
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// setup retrofit for http handler
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface JikanService {

    /**
     * get all current season anime list
     *
     * @return SeasonalProperty list of anime in the current season
     */
    @GET(SEASON)
    suspend fun getCurrentSeason(): SeasonalProperty

    /**
     * this method returns result of search by its title
     *
     * @query q String
     *  anime title
     *
     * @query page Int
     *  page of result, since there's possibility of similar title,
     *  the result is grouped by page
     */
    @GET(SEARCH)
    suspend fun search(@Query("q") q: String, @Query("page") page: Int = 1)

    /**
     * get top upcoming anime list
     *
     * @return
     */
    @GET("$TOP/anime/1/upcoming")
    suspend fun getTopUpcoming(): UpcomingProperty
}

object JikanNetwork {
    val service: JikanService by lazy {
        retrofit.create(JikanService::class.java)
    }
}


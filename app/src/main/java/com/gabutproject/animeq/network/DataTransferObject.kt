package com.gabutproject.animeq.network

data class Genre (
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Producer(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class AnimeProperty (
    val mal_id: Int,
    val url: String,
    val title: String,
    val image_url: String,
    val synopsis: String,
    val type: String,
    val airing_start: String?,
    val episodes: Int?,
    val members: Int?,
    val genres: List<Genre>,
    val source: String,
    val producers: List<Producer>?,
    val score: Float?,
    val licencors: List<String>?,
    val r18: Boolean,
    val kids: Boolean,
    val continuing: Boolean
)

data class SeasonalProperty (
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<AnimeProperty>
)
package com.gabutproject.animeq.network

data class Genre(
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

data class Licensor(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Studio(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class SeasonalAnimeProperty(
    val mal_id: Int,
    val url: String,
    val title: String,
    val image_url: String,
    val synopsis: String?,
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

data class SeasonalProperty(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val season_name: String,
    val season_year: Int,
    val anime: List<SeasonalAnimeProperty>
)

data class UpcomingAnimeProperty(
    val mal_id: Int,
    val rank: Int,
    val title: String,
    val url: String,
    val image_url: String,
    val type: String,
    val episodes: Int?,
    val start_date: String?,
    val end_date: String?,
    val members: Int,
    val score: Int
)

data class UpcomingProperty(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val top: List<UpcomingAnimeProperty>
)

data class RelatedPropery(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Related(
    val Adaptation: List<RelatedPropery> = emptyList(),
    val Sequel: List<RelatedPropery> = emptyList(),
    val Prequel: List<RelatedPropery> = emptyList()
)

data class AiredDate(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

data class AiredProperty(
    val from: AiredDate,
    val to: AiredDate
)

data class Aired(
    val from: String?,
    val to: String?,
    val prop: AiredProperty,
    val string: String?
)

data class AnimeProperty(
    val request_hash: String,
    val request_cached: Boolean,
    val request_cache_expiry: Int,
    val mal_id: Int,
    val url: String,
    val image_url: String,
    val trailer_url: String?,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>,
    val type: String,
    val source: String,
    val episodes: Int?,
    val status: String,
    val airing: Boolean,
    val aired: Aired,
    val duration: String,
    val rating: String,
    val score: Double?,
    val scored_by: Int?,
    val rank: Int?,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String,
    val background: String?,
    val related: Related,
    val producers: List<Producer>,
    val licensors: List<Licensor>,
    val studios: List<Studio>,
    val genres: List<Genre>,
    val opening_themes: List<String>,
    val ending_themes: List<String>
)

data class Result(
	val mal_id: Int,
	val url: String,
	val image_url: String,
	val title: String,
	val airing: Boolean,
	val synopsis: String,
	val type: String,
	val episodes: Int,
	val score: Double,
	val start_date: String?,
	val end_date: String?,
	val members: Int,
	val rated: String?
)

data class SearchProperty(
	val results: List<Result>,
	val last_page: Int
)

data class Bookmark(
    val mal_id: Int,
    val title: String,
    val img_url: String
)
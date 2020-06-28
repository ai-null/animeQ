package com.gabutproject.animeq.network

import org.w3c.dom.Text

//{
//  "mal_id": 10162,
//  "url": "https://myanimelist.net/anime/10162/Usagi_Drop",
//  "title": "Usagi Drop",
//  "image_url": "https://cdn.myanimelist.net/images/anime/2/29665.jpg",
//  "synopsis": "Daikichi Kawachi is a 30-year-old bachelor working a respectable job but otherwise wandering aimlessly through life. When his grandfather suddenly passes away, he returns to the family home to pay his respects. Upon arriving at the house, he meets a mysterious young girl named Rin who, to Daikichi’s astonishment, is his grandfather's illegitimate daughter!\r\n \r\nThe shy and unapproachable girl is deemed an embarrassment to the family, and finds herself ostracized by her father's relatives, all of them refusing to take care of her in the wake of his death. Daikichi, angered by their coldness towards Rin, announces that he will take her in—despite the fact that he is a young, single man with no prior childcare experience.\r\n\r\nUsagi Drop is the story of Daikichi's journey through fatherhood as he raises Rin with his gentle and affectionate nature, as well as an exploration of the warmth and interdependence that are at the heart of a happy, close-knit family.\r\n\r\n[Written by MAL Rewrite]",
//  "type": "TV",
//  "airing_start": "2011-07-07T15:45:00+00:00",
//  "episodes": 11,
//  "members": 353444,
//  "genres": [],
//  "source": "Manga",
//  "producers": [],
//  "score": 8.46,
//  "licensors": [],
//  "r18": false,
//  "kids": false,
//  "continuing": false
//}

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
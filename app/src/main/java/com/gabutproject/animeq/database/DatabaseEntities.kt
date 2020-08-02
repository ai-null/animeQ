package com.gabutproject.animeq.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntities(
    @PrimaryKey
    val mal_id: Int
)
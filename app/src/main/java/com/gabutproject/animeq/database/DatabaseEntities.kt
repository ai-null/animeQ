package com.gabutproject.animeq.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntities constructor(
    @PrimaryKey
    val mal_id: Int,
    @ColumnInfo(name = "title")
    val title: String
)
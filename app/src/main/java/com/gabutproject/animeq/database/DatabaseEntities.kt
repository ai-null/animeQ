package com.gabutproject.animeq.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabutproject.animeq.network.Bookmark

@Entity(tableName = "bookmark")
data class BookmarkEntities constructor(
    @PrimaryKey
    val mal_id: Int,
    val title: String,
    val img_url: String
)

/**
 * Map databaseModel to DomainModel to show it on UI
 */
fun List<BookmarkEntities>.asDomainModel(): List<Bookmark> {
    return map {
        Bookmark(
            mal_id = it.mal_id,
            title = it.title,
            img_url = it.img_url
        )
    }
}
package com.gabutproject.animeq.repository

import android.app.Application
import com.gabutproject.animeq.database.AnimeQDatabase
import com.gabutproject.animeq.database.BookmarkEntities
import com.gabutproject.animeq.database.asDomainModel
import com.gabutproject.animeq.network.Bookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeQRepository constructor(application: Application) {
    private val database = AnimeQDatabase.getDatabase(application)

    /**
     * add bookmarked anime detail to the database
     *
     * @param mal_id Integer
     */
    suspend fun addBookmark(mal_id: Int, title: String, img_url: String) {
        withContext(Dispatchers.IO) {
            database.bookmarkDao.addBookmark(
                BookmarkEntities(
                    mal_id = mal_id,
                    title = title,
                    img_url = img_url
                )
            )
        }
    }

    /**
     * remove bookmarked from database
     *
     * @param mal_id Integer
     */
    suspend fun removeBookmark(mal_id: Int) {
        withContext(Dispatchers.IO) {
            database.bookmarkDao.removeBookmark(mal_id)
        }
    }

    /**
     * check status bookmark
     *
     * @param mal_id Integer
     */
    suspend fun checkBookmark(mal_id: Int): List<BookmarkEntities> {
        return withContext(Dispatchers.IO) {
            return@withContext database.bookmarkDao.getBookmarked(mal_id)
        }
    }

    suspend fun getBookmarks(): List<Bookmark> {
        return withContext(Dispatchers.IO) {
            return@withContext database.bookmarkDao.getBookmarks().asDomainModel()
        }
    }
}
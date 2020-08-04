package com.gabutproject.animeq.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gabutproject.animeq.database.AnimeQDatabase
import com.gabutproject.animeq.database.BookmarkEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeQRepository constructor(application: Application) {
    private val database = AnimeQDatabase.getDatabase(application)

    /**
     * add bookmarked anime detail to the database
     *
     * @param mal_id Integer
     */
    suspend fun addBookmark(mal_id: BookmarkEntities) {
        withContext(Dispatchers.IO) {
            database.bookmarkDao.addBookmark(mal_id)
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
}
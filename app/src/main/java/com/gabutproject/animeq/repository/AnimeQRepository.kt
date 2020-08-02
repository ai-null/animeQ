package com.gabutproject.animeq.repository

import android.app.Application
import com.gabutproject.animeq.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeQRepository constructor(application: Application) {
    private val database = getDatabase(application)

    /**
     * add bookmarked anime detail to the database
     *
     * @param mal_id Integer
     */
    suspend fun addBookmark(mal_id: Int) {
        withContext(Dispatchers.IO) {
            database.bookmarkDao.addBookmark(mal_id)
        }
    }

    /**
     * remove bookmared from database
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
    suspend fun checkBookmark(mal_id: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            return@withContext database.bookmarkDao.getBookmared(mal_id)
        }
    }
}
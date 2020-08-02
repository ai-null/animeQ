package com.gabutproject.animeq.database

import android.content.Context
import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(mal_id: Int)

    @Delete
    fun removeBookmark(mal_id: Int)

    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): List<Int>

    @Query("SELECT * FROM bookmark WHERE mal_id = :mal_id")
    fun getBookmared(mal_id: Int): List<Int>
}

@Database(version = 1, exportSchema = false, entities = [BookmarkEntities::class])
abstract class AnimeQDatabase : RoomDatabase() {
    abstract val bookmarkDao: BookmarkDao
}

lateinit var INSTANCE: AnimeQDatabase

fun getDatabase(context: Context): AnimeQDatabase {
    if (!::INSTANCE.isInitialized) {
        INSTANCE = Room.databaseBuilder(
            context.applicationContext, AnimeQDatabase::class.java, "animeq_database"
        ).build()
    }

    return INSTANCE
}
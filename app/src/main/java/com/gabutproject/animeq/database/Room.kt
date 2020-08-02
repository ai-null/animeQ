package com.gabutproject.animeq.database

import android.content.Context
import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(mal_id: Int)

    @Query("SELECT * FROM bookmark")
    fun getBookmark(): List<Int>
}

@Database(version = 1, exportSchema = false, entities = [BookmarkEntities::class])
abstract class AnimeQDatabase : RoomDatabase() {
    abstract val bookmarkDao: BookmarkDao
}

lateinit var INSTACE: AnimeQDatabase

fun getDatabase(context: Context): AnimeQDatabase {
    if (!::INSTACE.isInitialized) {
        INSTACE = Room.databaseBuilder(
            context.applicationContext, AnimeQDatabase::class.java, "animeq_database"
        ).build()
    }

    return INSTACE
}
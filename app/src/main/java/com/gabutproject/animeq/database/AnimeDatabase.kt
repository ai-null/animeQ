package com.gabutproject.animeq.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(data: BookmarkEntities)

    @Query("DELETE FROM bookmark WHERE mal_id = :mal_id")
    fun removeBookmark(mal_id: Int)

    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): List<BookmarkEntities>

    @Query("SELECT * FROM bookmark WHERE mal_id = :mal_id LIMIT 1")
    fun getBookmarked(mal_id: Int): List<BookmarkEntities>
}

@Database(entities = [BookmarkEntities::class], version = 2, exportSchema = false)
abstract class AnimeQDatabase : RoomDatabase() {

    abstract val bookmarkDao: BookmarkDao

    companion object {
        @Volatile
        private lateinit var instance: AnimeQDatabase

        fun getDatabase(context: Context): AnimeQDatabase {
            synchronized(AnimeQDatabase::class.java) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimeQDatabase::class.java,
                        "anime_database"
                    ).fallbackToDestructiveMigration().build()
                }

                return instance
            }
        }
    }
}

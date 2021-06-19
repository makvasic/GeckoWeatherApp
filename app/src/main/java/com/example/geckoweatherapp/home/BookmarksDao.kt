package com.example.geckoweatherapp.home

import androidx.room.*

@Dao
interface BookmarksDao {
    @Query("SELECT * FROM bookmarks")
    suspend fun getBookmarks(): List<Bookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarks(bookmarks: List<Bookmark>)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)
}
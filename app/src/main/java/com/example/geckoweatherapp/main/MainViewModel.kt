package com.example.geckoweatherapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.geckoweatherapp.AppDatabase
import com.example.geckoweatherapp.home.Bookmark
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appDatabase: AppDatabase by lazy { AppDatabase.getInstance(application) }

    private val _bookmarksLiveData = MutableLiveData<List<Bookmark>>()
    val bookmarksLiveData: LiveData<List<Bookmark>> = _bookmarksLiveData

    fun getBookmarks() {
        viewModelScope.launch {
            _bookmarksLiveData.value = appDatabase.bookmarksDao().getBookmarks()
        }
    }

    fun insertBookmarks(bookmarks: List<Bookmark>) {
        viewModelScope.launch {
            appDatabase.bookmarksDao().insertBookmarks(bookmarks)
            _bookmarksLiveData.value = appDatabase.bookmarksDao().getBookmarks()
        }
    }

    fun deleteBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            appDatabase.bookmarksDao().deleteBookmark(bookmark)
            _bookmarksLiveData.value = appDatabase.bookmarksDao().getBookmarks()
        }
    }

}
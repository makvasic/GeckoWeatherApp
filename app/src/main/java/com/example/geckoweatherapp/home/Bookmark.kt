package com.example.geckoweatherapp.home

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val latitude: Float,
    val longitude: Float
) : Parcelable
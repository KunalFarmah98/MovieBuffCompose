package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class Favourite(
    var title: String,
    var image: String,
    val genreIds: String,
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
)
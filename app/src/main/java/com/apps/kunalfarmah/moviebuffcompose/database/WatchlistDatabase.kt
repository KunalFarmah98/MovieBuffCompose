package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase

// different impl are in android and ios
@Database(entities = [Watchlist::class], version = 1)
abstract class WatchlistDatabase: RoomDatabase() {
    abstract fun favouriteDao(): WatchlistDao
}
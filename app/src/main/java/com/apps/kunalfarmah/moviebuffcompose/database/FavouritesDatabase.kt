package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase

// different impl are in android and ios
@Database(entities = [Favourite::class], version = 1)
abstract class FavouritesDatabase: RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}
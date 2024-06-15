package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase

// different impl are in android and ios
@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
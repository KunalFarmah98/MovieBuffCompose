package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Query("Select * from movies")
    fun getAllMovies(): List<MovieEntity>

    @Upsert
    suspend fun upsert(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("Delete from movies")
    suspend fun deleteAll()

}
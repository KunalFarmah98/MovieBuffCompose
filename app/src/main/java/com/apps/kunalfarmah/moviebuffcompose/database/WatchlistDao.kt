package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {
    @Query("Select * from watchlist")
    fun getAllFavourites(): Flow<List<Watchlist>>

    @Upsert
    suspend fun upsert(movie: Watchlist)

    @Delete
    suspend fun deleteFavourite(movie: Watchlist)

    @Query("Delete from watchlist")
    suspend fun deleteAll()

}
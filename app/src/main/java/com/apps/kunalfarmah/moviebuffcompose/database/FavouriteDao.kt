package com.apps.kunalfarmah.moviebuffcompose.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.apps.kunalfarmah.moviebuffcompose.database.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Query("Select * from favourites")
    fun getAllFavourites(): Flow<List<Favourite>>

    @Upsert
    suspend fun upsert(movie: Favourite)

    @Delete
    suspend fun deleteFavourite(movie: Favourite)

    @Query("Delete from favourites")
    suspend fun deleteAll()

}
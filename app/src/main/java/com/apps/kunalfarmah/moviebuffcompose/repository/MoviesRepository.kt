package com.apps.kunalfarmah.moviebuffcompose.repository


import android.util.Log
import com.apps.kunalfarmah.moviebuffcompose.database.MovieEntity
import com.apps.kunalfarmah.moviebuffcompose.database.MoviesDatabase
import com.apps.kunalfarmah.moviebuffcompose.model.Movie
import com.apps.kunalfarmah.moviebuffcompose.preferences.PreferenceManager
import com.apps.kunalfarmah.moviebuffcompose.util.Constants
import com.apps.kunalfarmah.moviebuffcompose.retrofit.*
import com.apps.kunalfarmah.moviebuffcompose.util.Util
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class MoviesRepository(
    private val movieRetrofit: MovieRetrofit,
    moviesDatabase: MoviesDatabase,
) {
    private val moviesDao = moviesDatabase.movieDao()
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    suspend fun fetchPopularMovies() : List<Movie> {
        return if (Util.isNetworkAvailable()) {
            try{
                val response = movieRetrofit.getPopularMovies().results
                if(response.isEmpty()){
                    moviesDao.getAllMovies().map { mapFromEntity(it) }
                }
                else {
                    Log.e("fetchPopularMovies", "data: $response")
                    response.map { moviesDao.upsert(mapToEntity(it)) }
                    response
                }
            }
            catch (e: Exception){
                Log.e("fetchPopularMovies", e.message.toString())
                moviesDao.getAllMovies().map { mapFromEntity(it) }
            }
        } else {
            Log.e("fetchPopularMovies", "no internet")
            moviesDao.getAllMovies().map { mapFromEntity(it) }
        }
    }

    suspend fun fetchTopRatedMovies() : List<Movie> {
        return if (Util.isNetworkAvailable()) {
            try{
                val response = movieRetrofit.getTopRatedMovies().results
                if(response.isEmpty()){
                    moviesDao.getAllMovies().map { mapFromEntity(it) }
                }
                else {
                    response.map { moviesDao.upsert(mapToEntity(it)) }
                    response
                }
            }
            catch (e: Exception){
                moviesDao.getAllMovies().map { mapFromEntity(it) }
            }
        } else {
            moviesDao.getAllMovies().map { mapFromEntity(it) }
        }
    }

    suspend fun searchMovies(query: String): List<Movie> {
        return if (Util.isNetworkAvailable()) {
            val response = movieRetrofit.searchMovies(query = query).results
            response
        } else
            emptyList()
    }

    suspend fun getMovieDetails(id: String): MovieDetailsResponse? {
        return if(Util.isNetworkAvailable()) {
            try{
                val response = movieRetrofit.getDetails(id)
                if(response == null){
                    getDetailsCache(id)
                }
                else{
                    PreferenceManager.putValue(
                        id + "_details",
                        moshi.adapter(MovieDetailsResponse::class.java).toJson(response)
                    )
                    response
                }
            }
            catch (e: Exception){
                getDetailsCache(id)
            }
        } else {
            getDetailsCache(id)
        }
    }

    private suspend fun getDetailsCache(id: String): MovieDetailsResponse? {
        val cache = PreferenceManager.getValue(id + "_details", "")?.first() as String
        return if(cache.isNotEmpty())
            moshi.adapter(MovieDetailsResponse::class.java).fromJson(cache)
        else
            null
    }

    suspend fun getMovieImages(id: String): List<PosterItem>? {
        return if(Util.isNetworkAvailable()) {
            try {
                val response = movieRetrofit.getImages(id, Constants.API_KEY)
                if(response == null){
                    getImagesCache(id)
                }
                else {
                    PreferenceManager.putValue(
                        id + "_images",
                        moshi.adapter(ImagesResponse::class.java).toJson(response)
                    )
                    response.posters
                }
            }
            catch (e:Exception){
                getImagesCache(id)
            }
        }
        else{
           getImagesCache(id)
        }
    }

    private suspend fun getImagesCache(id: String): List<PosterItem>? {
        val cache = PreferenceManager.getValue(id + "_images", "")?.first() as String
        return if(cache.isNotEmpty())
            moshi.adapter(ImagesResponse::class.java).fromJson(cache)?.posters
        else
            null
    }

    suspend fun getMovieReviews(id: String): List<ReviewItem>? {
        return if(Util.isNetworkAvailable()) {
            try {
                val response = movieRetrofit.getReviews(id, Constants.API_KEY)
                if(response.results.isEmpty()){
                    getReviewsCache(id)
                }
                else {
                    PreferenceManager.putValue(
                        id + "_reviews",
                        moshi.adapter(ReviewResponse::class.java).toJson(response)
                    )
                    response.results
                }
            }
            catch (e:Exception){
                getReviewsCache(id)
            }
        }
        else{
            getReviewsCache(id)
        }
    }

    private suspend fun getReviewsCache(id: String): List<ReviewItem>? {
        val cache = PreferenceManager.getValue(id + "_reviews", "")?.first() as String
        return if(cache.isNotEmpty())
            moshi.adapter(ReviewResponse::class.java).fromJson(cache)?.results
        else
            null
    }
    
    private fun mapToEntity(obj: Movie): MovieEntity {
        return MovieEntity(
            id = obj.id,
            title = obj.title,
            overview = obj.overview,
            posterPath = obj.posterPath ?: "",
            backdropPath = obj.backdropPath ?: "",
            releaseDate = obj.releaseDate ?: "",
            voteAverage = obj.voteAverage ?: 0.0,
            voteCount = obj.voteCount ?: 0,
            genreIds = moshi.adapter<List<Int>>(List::class.java).toJson(obj.genreIds),
            adult = obj.adult,
            originalLanguage = obj.originalLanguage ?: "",
            originalTitle = obj.originalTitle ?:"",
            popularity = obj.popularity,
            video = obj.video
        )
    }
    
    private fun mapFromEntity(obj: MovieEntity): Movie{
        return Movie(
            id = obj.id,
            title = obj.title,
            overview = obj.overview,
            posterPath = obj.posterPath,
            backdropPath = obj.backdropPath,
            releaseDate = obj.releaseDate,
            voteAverage = obj.voteAverage,
            voteCount = obj.voteCount,
            genreIds = moshi.adapter<List<Int>>(List::class.java).fromJson(obj.genreIds)?: emptyList(),
            adult = obj.adult,
            originalLanguage = obj.originalLanguage,
            originalTitle = obj.originalTitle,
            popularity = obj.popularity,
            video = obj.video
        )
            
    }
}
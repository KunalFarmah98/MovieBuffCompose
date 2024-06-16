package com.apps.kunalfarmah.moviebuffcompose.retrofit

import com.apps.kunalfarmah.moviebuffcompose.util.Constants
import retrofit2.http.*

interface MovieRetrofit {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey:String = Constants.API_KEY, @Query("page") page:Int = 1): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey:String = Constants.API_KEY, @Query("page") page:Int = 1): MoviesResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey:String = Constants.API_KEY, @Query("query") query:String): MoviesResponse

    @GET("movie/{movieId}")
    suspend fun getDetails(@Path("movieId") id:String, @Query("api_key") apiKey:String = Constants.API_KEY): MovieDetailsResponse

    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(@Path("movieId") id:String, @Query("api_key") apiKey:String = Constants.API_KEY):ReviewResponse

    @GET("movie/{movieId}/images")
    suspend fun getImages(@Path("movieId") id:String, @Query("api_key") apiKey:String = Constants.API_KEY): ImagesResponse

}
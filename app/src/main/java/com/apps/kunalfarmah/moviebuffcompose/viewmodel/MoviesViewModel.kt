package com.apps.kunalfarmah.moviebuffcompose.viewmodel

import androidx.lifecycle.*
import com.apps.kunalfarmah.moviebuffcompose.model.Movie
import com.apps.kunalfarmah.moviebuffcompose.repository.MoviesRepository
import com.apps.kunalfarmah.moviebuffcompose.retrofit.MovieDetailsResponse
import com.apps.kunalfarmah.moviebuffcompose.retrofit.PosterItem
import com.apps.kunalfarmah.moviebuffcompose.retrofit.ReviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _movies: Flow<List<Movie>> = flowOf(emptyList())
    private var _movieDetails: Flow<MovieDetailsResponse> = flowOf()
    private var _movieReviews: Flow<List<ReviewItem>> = flowOf(emptyList())
    private var _movieImages: Flow<List<PosterItem>> = flowOf(emptyList())


    val movies: Flow<List<Movie>>
        get() = _movies

    val movieDetails:Flow<MovieDetailsResponse>
        get() = _movieDetails

    val movieReviews: Flow<List<ReviewItem>>
        get() = _movieReviews

    val movieImages: Flow<List<PosterItem>>
        get() = _movieImages




    fun fetchAllPopularMovies() {
        viewModelScope.launch {
            _movies = moviesRepository.fetchPopularMovies()
        }
    }


    fun fetchAllTopRatedMovies() {
        viewModelScope.launch {
            _movies = moviesRepository.fetchTopRatedMovies()
        }
    }

    fun searchAllMovies(query:String){
        viewModelScope.launch {
            _movies = moviesRepository.searchMovies(query)
        }
    }


    fun getMovieDetail(id:String){
        viewModelScope.launch {
            val response = moviesRepository.getMovieDetails(id)
            if(response != null){
                _movieDetails = response as Flow<MovieDetailsResponse>
            }
        }
    }

    fun getMovieReviews(id:String){
        viewModelScope.launch {
            val response = moviesRepository.getMovieReviews(id)
            if(response!= null)
                _movieReviews  = response as Flow<List<ReviewItem>>
        }
    }

    fun getMovieImages(id:String){
        viewModelScope.launch {
            val response = moviesRepository.getMovieImages(id)
            if(response != null)
                _movieImages = response as Flow<List<PosterItem>>
        }
    }


}

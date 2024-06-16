package com.apps.kunalfarmah.moviebuffcompose.viewmodel

import androidx.lifecycle.*
import com.apps.kunalfarmah.moviebuffcompose.model.Movie
import com.apps.kunalfarmah.moviebuffcompose.repository.MoviesRepository
import com.apps.kunalfarmah.moviebuffcompose.retrofit.MovieDetailsResponse
import com.apps.kunalfarmah.moviebuffcompose.retrofit.PosterItem
import com.apps.kunalfarmah.moviebuffcompose.retrofit.ReviewItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    private var _movieDetails: MutableStateFlow<MovieDetailsResponse?> = MutableStateFlow(null)
    private var _movieReviews: MutableStateFlow<List<ReviewItem>> = MutableStateFlow(emptyList())
    private var _movieImages: MutableStateFlow<List<PosterItem>> = MutableStateFlow(emptyList())


    val movies = _movies.asStateFlow()

    val movieDetails = _movieDetails.asStateFlow()

    val movieReviews = _movieReviews.asStateFlow()

    val movieImages = _movieImages.asStateFlow()




    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO){
            _movies.value = moviesRepository.fetchPopularMovies()
        }
    }


    fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _movies.value = moviesRepository.fetchTopRatedMovies()
        }
    }

    fun searchAllMovies(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            _movies.value = moviesRepository.searchMovies(query)
        }
    }


    fun getMovieDetail(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = moviesRepository.getMovieDetails(id)
            if(response != null){
                _movieDetails.value = response
            }
        }
    }

    fun getMovieReviews(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = moviesRepository.getMovieReviews(id)
            if(response!= null)
                _movieReviews.value  = response
        }
    }

    fun getMovieImages(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = moviesRepository.getMovieImages(id)
            if(response != null)
                _movieImages.value = response
        }
    }


}

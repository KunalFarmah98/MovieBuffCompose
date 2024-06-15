package com.apps.kunalfarmah.moviebuffcompose.retrofit

import com.apps.kunalfarmah.moviebuffcompose.model.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)


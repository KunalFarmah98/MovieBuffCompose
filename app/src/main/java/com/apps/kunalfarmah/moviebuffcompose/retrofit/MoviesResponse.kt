package com.apps.kunalfarmah.moviebuffcompose.retrofit

import com.apps.kunalfarmah.moviebuffcompose.model.Movie
import com.squareup.moshi.Json

data class MoviesResponse(
    @Json (name = "page") val page: Int?,
    @Json (name = "results") val results: List<Movie>,
    @Json (name = "total_pages") val totalPages: Int?,
    @Json (name = "total_results") val totalResults: Int?
)


package com.apps.kunalfarmah.moviebuffcompose.retrofit

data class ImagesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<PosterItem>
)

data class Backdrop(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso6391: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)

data class Logo(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso6391: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)

data class PosterItem(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso6391: String?,
    val voteAverage: Double, val voteCount: Int,
    val width: Int
)
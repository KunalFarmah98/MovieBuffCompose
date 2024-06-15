package com.apps.kunalfarmah.moviebuffcompose.retrofit

data class ReviewResponse(
    val id:Int,
    val page: Int,
    val results: List<ReviewItem>,
    val totalPages: Int,
    val totalResults: Int
)

data class ReviewItem(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)

data class AuthorDetails(
    val avatarPath: String?,
    val name: String,
    val rating: Int?,
    val username: String
)


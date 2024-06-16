package com.apps.kunalfarmah.moviebuffcompose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.apps.kunalfarmah.moviebuffcompose.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel, type: String) {

    if (type == "popular") {
        viewModel.getPopularMovies()
    } else if (type == "topRated") {
        viewModel.getTopRatedMovies()
    }

    val moviesList by type.let {
        when (it) {
            "popular" -> {
                viewModel.popularMovies.collectAsState(emptyList())
            }

            "topRated" -> {
                viewModel.topRatedMovies.collectAsState(emptyList())
            }

            "search" -> {
                viewModel.searchedMovies.collectAsState(emptyList())
            }
            else ->{
                viewModel.popularMovies.collectAsState(emptyList())
            }
        }
    }
    Text(text = moviesList.toString())
}
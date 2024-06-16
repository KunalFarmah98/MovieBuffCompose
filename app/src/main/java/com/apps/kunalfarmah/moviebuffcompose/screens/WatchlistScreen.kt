package com.apps.kunalfarmah.moviebuffcompose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.apps.kunalfarmah.moviebuffcompose.viewmodel.MoviesViewModel

@Composable
fun WatchlistScreen(navController: NavController, viewModel: MoviesViewModel) {
    Text(text = "watchlist")
}
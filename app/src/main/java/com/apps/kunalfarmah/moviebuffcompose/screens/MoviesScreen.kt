package com.apps.kunalfarmah.moviebuffcompose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.apps.kunalfarmah.moviebuffcompose.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel, type: String) {
    Text(text = type)
}
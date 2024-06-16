package com.apps.kunalfarmah.moviebuffcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apps.kunalfarmah.moviebuffcompose.nav.BottomTabs
import com.apps.kunalfarmah.moviebuffcompose.nav.NavigationItem
import com.apps.kunalfarmah.moviebuffcompose.screens.MoviesScreen
import com.apps.kunalfarmah.moviebuffcompose.screens.WatchlistScreen
import com.apps.kunalfarmah.moviebuffcompose.ui.theme.MovieBuffComposeTheme
import com.apps.kunalfarmah.moviebuffcompose.viewmodel.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieBuffComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val moviesViewModel:MoviesViewModel = koinViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomTabs(navController) }) { innerPadding ->
        NavHost(navController, startDestination = NavigationItem.Popular.route, Modifier.padding(innerPadding)) {
            composable(NavigationItem.Popular.route) {
                MoviesScreen(navController = navController, viewModel = moviesViewModel, type = "popular")
            }
            composable(NavigationItem.TopRated.route) {
                MoviesScreen(navController = navController, viewModel = moviesViewModel, type = "topRated")
            }
            composable(NavigationItem.Search.route) {
                MoviesScreen(navController = navController, viewModel = moviesViewModel, type = "search")
            }
            composable(NavigationItem.WatchList.route) {
                WatchlistScreen(navController = navController, viewModel = moviesViewModel)
            }
        }
    }
}


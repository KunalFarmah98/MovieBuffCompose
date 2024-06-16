package com.apps.kunalfarmah.moviebuffcompose.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class NavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    data object Popular : NavigationItem("Popular", Icons.Rounded.Home, "Popular")
    data object TopRated : NavigationItem("TopRated", Icons.Rounded.Star, "Top Rated")
    data object Search : NavigationItem("Search", Icons.Rounded.Search, "Search")
    data object WatchList : NavigationItem("Watchlist", Icons.Rounded.List, "Watchlist")
}

@Preview
@Composable
fun BottomTabs(navController: NavController = rememberNavController()) {

    val items = listOf(
        NavigationItem.Popular,
        NavigationItem.TopRated,
        NavigationItem.Search,
        NavigationItem.WatchList,
    )
    NavigationBar() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon!!, contentDescription = "Popular Movies"
                    )
                },
                label = {
                    Text(text = screen.title)
                })
        }
    }
}
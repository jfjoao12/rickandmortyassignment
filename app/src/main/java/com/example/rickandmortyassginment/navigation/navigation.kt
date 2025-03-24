package com.example.rickandmortyassginment.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rickandmortyassginment.destinations.Destination

@Composable
fun BottomNav(navController: NavController) {
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        // Repairs/main navbar item
        NavigationBarItem(
            selected = currentDestination?.route == Destination.Main.route,
            onClick = {
                navController.navigate(Destination.Main.route) {
                    popUpTo(Destination.Main.route)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Rounded.AccountCircle,
                    contentDescription = "All"
                )
            },
            label = { Text(text = Destination.Main.route) }
        )
        NavigationBarItem(
            selected = currentDestination?.route == Destination.Favourite.route,
            onClick = {
                navController.navigate(Destination.Favourite.route) {
                    popUpTo(Destination.Main.route)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = "Favourites"
                )
            },
            label = { Text(text = Destination.Favourite.route) }
        )
    }
}
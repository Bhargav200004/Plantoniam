package com.example.plantoniam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.plantoniam.ui.homeScreen.HomeScreen
import com.example.plantoniam.ui.imageScreen.ImageScreen

@Composable
fun NavGraphSetup() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreenNavigation
    ){

        composable<Route.HomeScreenNavigation> {
            HomeScreen(navController)
        }

        composable<Route.ImageScreenNavigation> { navBackStackEntry ->
            val id = navBackStackEntry.toRoute<Route.ImageScreenNavigation>().id
            ImageScreen(navController)
        }
    }

}
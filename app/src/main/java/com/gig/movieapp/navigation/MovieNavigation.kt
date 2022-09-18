package com.gig.movieapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gig.movieapp.views.fragments.DetailFragment
import com.gig.movieapp.views.fragments.HomeFragment

@ExperimentalMaterial3Api
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieViews.HomeFragment.name) {
        composable(MovieViews.HomeFragment.name) {
            HomeFragment(navController = navController)
        }

        composable(MovieViews.DetailFragment.name + "/{movie}", arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })) {
            DetailFragment(navController = navController, it.arguments?.getString("movie"))
        }
    }
}

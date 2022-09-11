package com.gig.movieapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

        composable(MovieViews.DetailFragment.name) {
            DetailFragment(navController = navController)
        }
    }
}

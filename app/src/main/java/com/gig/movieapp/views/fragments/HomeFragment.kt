package com.gig.movieapp.views.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gig.movieapp.models.Movie
import com.gig.movieapp.models.getMovies
import com.gig.movieapp.navigation.MovieViews
import com.gig.movieapp.widgets.MovieRow

@ExperimentalMaterial3Api
@Composable
fun HomeFragment(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movie Selection")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(
                navController = navController,
                modifier = Modifier
                    .padding(top = it.calculateTopPadding()),
                movieList = getMovies()
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun HomeContent(
    navController: NavController,
    modifier: Modifier,
    movieList: List<Movie> = listOf()
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 0.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = "${MovieViews.DetailFragment.name}/$movie")
                }
            }
        }
    }
}

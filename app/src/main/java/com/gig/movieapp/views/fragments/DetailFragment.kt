package com.gig.movieapp.views.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gig.movieapp.models.Movie
import com.gig.movieapp.models.getMovies
import com.gig.movieapp.utilities.extensions.default
import com.gig.movieapp.widgets.MovieRow

@ExperimentalMaterial3Api
@Composable
fun DetailFragment(navController: NavController, movieId: String?) {
    val movie = getMovies().find { it.id == movieId }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = movie?.title.default(""))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent, scrolledContainerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Home Screen"
                        )
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            DetailContent(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding()),
                movie
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun DetailContent(
    modifier: Modifier,
    movie: Movie? = null
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieRow(movie = movie)
    }
}

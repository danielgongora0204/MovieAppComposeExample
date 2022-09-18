package com.gig.movieapp.views.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gig.movieapp.navigation.MovieViews
import com.gig.movieapp.ui.theme.BackgroundLight

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
                    .padding(top = it.calculateTopPadding())
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun HomeContent(
    navController: NavController,
    modifier: Modifier,
    movieList: List<String> = listOf(
        "Harry Potter",
        "300",
        "Spider-man",
        "Avatar",
        "Avengers",
        "La la land",
        "007: Casino Royale",
        "Click",
        "Batman"
    )
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 0.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieViews.DetailFragment.name + "/$movie")
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun MovieRow(movie: String, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundLight),
        onClick = { onItemClick.invoke(movie) }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                color = BackgroundLight,
                shape = RectangleShape,
                tonalElevation = 4.dp,
                shadowElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Movie Image"
                )
            }

            Text(text = movie, color = Color.Black)
        }
    }
}

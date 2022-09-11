package com.gig.movieapp.views.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun DetailFragment(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movie Selection")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray),
            )
        }
    ) {
        MainContent(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun MainContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 0.dp)
            .fillMaxSize()
    ) {
        Text(text = "Hello From Details")
    }
}

package com.gig.movieapp.views.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun DetailFragment(navController: NavController, movieName: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "$movieName")
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
                    .padding(top = it.calculateTopPadding())
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun DetailContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello From Details")
    }
}

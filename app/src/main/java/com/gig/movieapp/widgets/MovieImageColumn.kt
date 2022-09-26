package com.gig.movieapp.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Preview
@ExperimentalMaterial3Api
@Composable
fun MovieImageColumn(image: String? = null, onItemClick: (String) -> Unit = {}, movieTitleVisibility: Boolean = true) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .size(240.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        SubcomposeAsyncImage(
            model = image,
            loading = {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(40.dp))
            },
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
        )
    }
}

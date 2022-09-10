package com.gig.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gig.movieapp.ui.theme.BackgroundLight
import com.gig.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                MainContent(it)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(content: @Composable (Modifier) -> Unit) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Hello from application")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray),
                )
            }
        ) {
            content(
                Modifier
                    .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    movieList: List<String> = listOf("Hello", "There", "Person")
) {
    Column(
        modifier = modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it)
            }
        }
    }
}

@Composable
fun MovieRow(movie: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundLight)
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App {
        MainContent(it)
    }
}

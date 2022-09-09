package com.gig.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding()))
        }
    }
}

@Composable
fun MainContent(modifier: Modifier, movieList: List<String> = listOf("Hello","There", "Person")) {
    Column(modifier = modifier.padding(12.dp).fillMaxSize()) {
        LazyColumn {
            items(items = movieList) {
                Text(text = it, color = Color.Black)
            }
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
package com.gig.movieapp.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gig.movieapp.navigation.MovieNavigation
import com.gig.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                MovieNavigation()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App {
        MovieNavigation()
    }
}

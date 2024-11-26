package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.movie.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MovieTvViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            MovieTheme {
                MovieTvScreen(viewModel)
            }
        }
    }
}

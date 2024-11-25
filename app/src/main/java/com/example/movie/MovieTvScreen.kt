package com.example.movie


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTvScreen(viewModel: MovieTvViewModel) {
    val movieTvState by viewModel.movieTv.observeAsState(emptyList())
    val movieTv = remember { MutableStateFlow("") }
    val movieTvStatequery by movieTv.collectAsState()

    LaunchedEffect(movieTvStatequery) {
        viewModel.fetchMovieTv(page = 1)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Movies & TV Shows") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            OutlinedTextField(
                value = movieTvStatequery,
                onValueChange = { movieTv.value = it },
                label = { Text("Enter movie or TV show name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Button(
                onClick = { viewModel.fetchMovieTv(page = 1) },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Search")
            }

            Box(modifier = Modifier.weight(1f)) {
                when {
                    movieTvState.isEmpty() -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    else -> {
                        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                            itemsIndexed(movieTvState) { index, result ->
                                Movie(result)

                                if (index >= movieTvState.size - 5 && !viewModel.isFetching) {
                                    viewModel.fetchNextPage()
                                }
                            }

                            if (viewModel.isFetching) {
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun Movie(result: Result) {
    val imageUrls = listOf(
        "https://image.tmdb.org/t/p/w500//3rxoUVI74z7rTWYSAC2q3Uax2nC.jpg",
        "https://image.tmdb.org/t/p/w500//5lBAGugE2S5HKsFIQfjRHLOwr5S.jpg",
        "https://image.tmdb.org/t/p/w500//fBClDP2SLBn4NlqYSMkZwky3VEp.jpg",
        "https://image.tmdb.org/t/p/w500//uVlUu174iiKhsUGqnOSy46eIIMU.jpg",
        "https://image.tmdb.org/t/p/w500//5y4cSOkBHoacJbymwldlDQtdN1W.jpg",
        "https://image.tmdb.org/t/p/w500//6lE2e6j8qbtQR8aHxQNJlwxdmKV.jpg",
        "https://image.tmdb.org/t/p/w500//lBoHzOgft2QfpjkVVvZCqeM4ttT.jpg",
        "https://image.tmdb.org/t/p/w500//q8eejQcg1bAqImEV8jh8RtBD4uH.jpg",
        "https://image.tmdb.org/t/p/w500//euYIwmwkmz95mnXvufEmbL6ovhZ.jpg",
        "https://image.tmdb.org/t/p/w500//kMZIMqEXO5MFd5Y1Ha2jZZF4pvF.jpg",
        "https://image.tmdb.org/t/p/w500//qEFTuoFIAwrnVn7IsvE8RVt2TK3.jpg"
    )
    val randomImageUrl = imageUrls[Random.nextInt(imageUrls.size)]


    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .height(300.dp)
            .clickable {}
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(randomImageUrl),
                contentDescription = "Random movie poster",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.7f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF4CAF50),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "⭐ ${result.voteAverage.toString()}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = result.title ?: result.name ?: "Unknown Title",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = result.firstAirDate ?: "Unknown Release Date",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

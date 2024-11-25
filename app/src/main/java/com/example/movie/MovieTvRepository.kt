package com.example.movie

import com.example.movie.RetrofitInstance.movieTvService

class MovieTvRepository {
    suspend fun getMovieTv(apiKey: Int): MovieTv {
        return movieTvService.getPopularMovies(apiKey)
    }
}
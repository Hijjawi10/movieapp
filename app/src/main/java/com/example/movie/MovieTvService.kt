package com.example.movie

import retrofit2.http.GET
import retrofit2.http.Query


interface MovieTvService {
    @GET("trending/all/day")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ):MovieTv
}

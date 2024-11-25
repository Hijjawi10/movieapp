package com.example.movie

class GetMovieTvUseCase(private val movieTvRepository: MovieTvRepository){
    suspend operator fun invoke(apiKey: Int): MovieTv {
        return movieTvRepository.getMovieTv(apiKey)
    }
}
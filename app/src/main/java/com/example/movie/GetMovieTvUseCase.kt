package com.example.movie

import javax.inject.Inject

class GetMovieTvUseCase @Inject constructor(
    private val movieTvRepository: MovieTvRepository
) {
    suspend operator fun invoke(page: Int) = movieTvRepository.getMovieTv(page)
}
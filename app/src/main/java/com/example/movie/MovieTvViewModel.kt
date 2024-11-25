package com.example.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieTvViewModel : ViewModel() {
    private val movieTvRepository = MovieTvRepository()
    private val movieTvUseCase = GetMovieTvUseCase(movieTvRepository)

    private val _movieTv = MutableLiveData<List<Result>>()
    val movieTv: LiveData<List<Result>> = _movieTv

    private var currentPage = 1
    var isFetching = false
    private var hasMorePages = true

    fun fetchMovieTv(page: Int) {
        if (isFetching || !hasMorePages) return
        isFetching = true

        viewModelScope.launch {
            try {
                val movieTvResponse = movieTvUseCase(page)

                val currentResults = _movieTv.value.orEmpty()
                _movieTv.value = currentResults + movieTvResponse.results

                currentPage = page
                hasMorePages = movieTvResponse.results.isNotEmpty()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isFetching = false
            }
        }
    }

    fun fetchNextPage() {
        fetchMovieTv(currentPage + 1)
    }
}

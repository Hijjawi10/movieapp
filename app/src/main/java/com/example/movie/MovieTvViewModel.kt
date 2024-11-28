package com.example.movie


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieTvViewModel : ViewModel() {
    private val movieTvRepository = MovieTvRepository()
    private val movieTvUseCase = GetMovieTvUseCase(movieTvRepository)

    private val _movieTv = MutableStateFlow<List<Result>>(emptyList())
    val movieTv: StateFlow<List<Result>> = _movieTv

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

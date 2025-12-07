package com.example.lab_week_13

import com.example.lab_week_13.api.MovieService
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {

    // TODO: Ensure your API Key is still here
    private val apiKey = "9221d7c6f4ea2de56a120fada76ba121"

    // Fetch movies from the API
    // This function returns a Flow of Movie objects
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            // Emit the list of popular movies from the API
            emit(movieService.getPopularMovies(apiKey).results)
        }.flowOn(Dispatchers.IO) // Run this coroutine on the IO thread
    }
}
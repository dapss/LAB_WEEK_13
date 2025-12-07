package com.example.lab_week_13.repository

import com.example.lab_week_13.api.MovieService
import com.example.lab_week_13.database.MovieDatabase
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) {
    private val apiKey = "9221d7c6f4ea2de56a120fada76ba121"

    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            // 1. Check local database first
            val movieDao = movieDatabase.movieDao()
            val savedMovies = movieDao.getMovies()

            if (savedMovies.isEmpty()) {
                // 2. If DB is empty, fetch from API
                val movies = movieService.getPopularMovies(apiKey).results

                // 3. Save to DB
                movieDao.addMovies(movies)

                // 4. Emit API data
                emit(movies)
            } else {
                // 5. If DB has data, emit DB data (Offline support)
                emit(savedMovies)
            }
        }.flowOn(Dispatchers.IO)
    }
}
package com.example.lab_week_13.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass // Import this

@JsonClass(generateAdapter = true)
data class PopularMoviesResponse(
    @Json(name = "results")
    val results: List<Movie>
)
package com.example.lab_week_13.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "movies") // 1. Add Entity annotation
@JsonClass(generateAdapter = true)
data class Movie(
    @PrimaryKey // 2. Set ID as Primary Key
    val id: Int,
    val title: String,
    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    val popularity: Double,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null,

    // 3. Ensure this is nullable to prevent crashes
    @Json(name = "backdrop_path")
    val backdropPath: String? = null
)
package ru.gb.makulin.mymoviefinder.facade

data class MovieDTO(
val adult:Boolean,
val genres: List<GenreDTO>,
val id: Long,
val original_title: String,
val title: String,
val overview: String,
val popularity: Double,
val poster_path: String,
val release_date: String,
val runtime: Long,
val tagline: String,
val voteAverage: Double,
val voteCount: Long
)

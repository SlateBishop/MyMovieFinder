package ru.gb.makulin.mymoviefinder.facade

data class TopRatedResultDTO(
    val adult: Boolean,
    val id: Long,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
)

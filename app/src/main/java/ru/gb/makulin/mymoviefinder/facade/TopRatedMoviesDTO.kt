package ru.gb.makulin.mymoviefinder.facade

data class TopRatedMoviesDTO(
    val page: Long,
    val results: List<TopRatedResultDTO>
)

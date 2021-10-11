package ru.gb.makulin.mymoviefinder.facade

data class MoviesListDTO(
    val page: Int,
    val results: List<MoviesListResultDTO>
)

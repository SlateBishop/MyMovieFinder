package ru.gb.makulin.mymoviefinder.facade.main

data class MoviesListDTO(
    val page: Int,
    val results: List<MoviesListResultDTO>
)

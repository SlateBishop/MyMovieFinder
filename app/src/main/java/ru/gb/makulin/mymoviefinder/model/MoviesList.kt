package ru.gb.makulin.mymoviefinder.model

data class MoviesList(
    val page: Int,
    val results: MutableList<MoviesListResult>
)
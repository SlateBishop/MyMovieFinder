package ru.gb.makulin.mymoviefinder.facade

import ru.gb.makulin.mymoviefinder.model.Movie

interface Repository {
    fun getTopRatedMoviesFromRemoteSource(): List<Movie>
    fun getNewMoviesFromRemoteSource(): List<Movie>
    fun getUpcomingMoviesFromRemoteSource(): List<Movie>
}
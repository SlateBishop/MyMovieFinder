package ru.gb.makulin.mymoviefinder.facade

import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.model.getNewMovies
import ru.gb.makulin.mymoviefinder.model.getTopRatedMovies
import ru.gb.makulin.mymoviefinder.model.getUpcomingMovies

class RepositoryImpl : Repository {
    override fun getTopRatedMoviesFromRemoteSource(): List<Movie> {
        return getTopRatedMovies()
    }

    override fun getNewMoviesFromRemoteSource(): List<Movie> {
        return getNewMovies()
    }

    override fun getUpcomingMoviesFromRemoteSource(): List<Movie> {
        return getUpcomingMovies()
    }

}
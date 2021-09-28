package ru.gb.makulin.mymoviefinder.facade

import ru.gb.makulin.mymoviefinder.model.getNewMovies
import ru.gb.makulin.mymoviefinder.model.getTopRatedMovies
import ru.gb.makulin.mymoviefinder.model.getUpcomingMovies

class RepositoryImpl : Repository {
    override fun getTopRatedMoviesFromRemoteSource() = getTopRatedMovies()

    override fun getNewMoviesFromRemoteSource() = getNewMovies()

    override fun getUpcomingMoviesFromRemoteSource() = getUpcomingMovies()

}
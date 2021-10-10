package ru.gb.makulin.mymoviefinder.facade

interface TopRatedLoaderListener {
    fun onLoaded(moviesDTO: TopRatedMoviesDTO)
    fun onFailed(throwable: Throwable)
}
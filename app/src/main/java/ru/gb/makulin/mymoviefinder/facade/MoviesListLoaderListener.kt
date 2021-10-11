package ru.gb.makulin.mymoviefinder.facade

interface MoviesListLoaderListener {
    fun onLoaded(moviesDTO: List<MoviesListDTO>)
    fun onFailed(throwable: Throwable)
}
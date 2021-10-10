package ru.gb.makulin.mymoviefinder.facade

interface MoviesListLoaderListener {
    fun onLoaded(moviesDTO: MoviesListDTO, listType: String)
    fun onFailed(throwable: Throwable)
}
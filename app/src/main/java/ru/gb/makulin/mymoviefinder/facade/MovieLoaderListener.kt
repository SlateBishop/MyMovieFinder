package ru.gb.makulin.mymoviefinder.facade

interface MovieLoaderListener {
    fun onLoaded(movieDTO: MovieDTO)
    fun onFailed(throwable: Throwable)
}
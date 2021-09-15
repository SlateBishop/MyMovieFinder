package ru.gb.makulin.mymoviefinder.facade

import ru.gb.makulin.mymoviefinder.model.Movie

interface Repository {
    fun getDataFromRemoteSource(): Movie
}
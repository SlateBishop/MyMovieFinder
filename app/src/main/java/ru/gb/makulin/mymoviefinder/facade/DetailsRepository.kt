package ru.gb.makulin.mymoviefinder.facade

interface DetailsRepository {

    fun getMovieDetailsFromServer(
        callback: retrofit2.Callback<MovieDTO>,
        movieId: Int,
        lang: String = "ru-RU"
    )
}
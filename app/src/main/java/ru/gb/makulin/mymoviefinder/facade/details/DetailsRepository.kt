package ru.gb.makulin.mymoviefinder.facade.details

interface DetailsRepository {

    fun getMovieDetailsFromServer(
        callback: retrofit2.Callback<MovieDTO>,
        movieId: Int,
        lang: String = "ru-RU"
    )
}
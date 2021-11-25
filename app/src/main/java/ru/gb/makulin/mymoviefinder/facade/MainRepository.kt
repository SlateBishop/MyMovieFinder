package ru.gb.makulin.mymoviefinder.facade

interface MainRepository {
    fun getTopRatedMoviesListFromServer(
        callback: retrofit2.Callback<MoviesListDTO>,
        page: Int = 1,
        lang: String = "ru-RU"
    )

    fun getNowPlayingMoviesListFromServer(
        callback: retrofit2.Callback<MoviesListDTO>,
        page: Int = 1,
        lang: String = "ru-RU"
    )

    fun getUpcomingMoviesListFromServer(
        callback: retrofit2.Callback<MoviesListDTO>,
        page: Int = 1,
        lang: String = "ru-RU"
    )

    fun getMovieDetailsFromServer(
        callback: retrofit2.Callback<MovieDTO>,
        movieId: Int,
        lang: String = "ru-RU"
    )
}
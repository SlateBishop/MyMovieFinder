package ru.gb.makulin.mymoviefinder.facade

import retrofit2.Callback

class MainRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MainRepository {
    override fun getTopRatedMoviesListFromServer(
        callback: Callback<MoviesListDTO>,
        page: Int,
        lang: String
    ) {
        remoteDataSource.getTopRatedMoviesList(callback, page, lang)
    }

    override fun getNowPlayingMoviesListFromServer(
        callback: Callback<MoviesListDTO>,
        page: Int,
        lang: String
    ) {
        remoteDataSource.getNowPlayingMoviesList(callback, page, lang)
    }

    override fun getUpcomingMoviesListFromServer(
        callback: Callback<MoviesListDTO>,
        page: Int,
        lang: String
    ) {
        remoteDataSource.getUpcomingMoviesList(callback, page, lang)
    }
}
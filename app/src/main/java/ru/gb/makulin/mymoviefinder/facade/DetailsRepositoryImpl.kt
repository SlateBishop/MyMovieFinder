package ru.gb.makulin.mymoviefinder.facade

import retrofit2.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {

    override fun getMovieDetailsFromServer(
        callback: Callback<MovieDTO>,
        movieId: Int,
        lang: String
    ) {
        remoteDataSource.getMovieDetails(callback, movieId, lang)
    }
}
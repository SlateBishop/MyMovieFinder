package ru.gb.makulin.mymoviefinder.facade.details

import retrofit2.Callback
import ru.gb.makulin.mymoviefinder.facade.RemoteDataSource

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {

    override fun getMovieDetailsFromServer(
        callback: Callback<MovieDTO>,
        movieId: Int,
        lang: String
    ) {
        remoteDataSource.getMovieDetails(callback, movieId, lang)
    }
}
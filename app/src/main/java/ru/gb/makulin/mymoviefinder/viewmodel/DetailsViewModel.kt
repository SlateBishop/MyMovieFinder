package ru.gb.makulin.mymoviefinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gb.makulin.mymoviefinder.app.MyApp.Companion.getHistoryDAO
import ru.gb.makulin.mymoviefinder.facade.RemoteDataSource
import ru.gb.makulin.mymoviefinder.facade.details.DetailsRepositoryImpl
import ru.gb.makulin.mymoviefinder.facade.details.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.history.LocalRepositoryImpl
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.utils.convertMovieDtoToMovie
import ru.gb.makulin.mymoviefinder.utils.convertMovieToHistoryEntity

class DetailsViewModel(
    private val detailsLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepositoryImpl = DetailsRepositoryImpl(
        RemoteDataSource()
    ),
    private val historyRepositoryImpl: LocalRepositoryImpl = LocalRepositoryImpl(getHistoryDAO())
) : ViewModel() {

    fun getLiveData() = detailsLiveDataToObserve

    fun getMovieFromRemote(movieId: Int) {
        detailsLiveDataToObserve.value = AppState.Loading
        getDataFromServer(callback, movieId)
    }

    private fun getDataFromServer(callback: Callback<MovieDTO>, movieId: Int) {
        detailsRepositoryImpl.getMovieDetailsFromServer(callback, movieId)
    }

    private val callback = object : retrofit2.Callback<MovieDTO> {
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            if (response.isSuccessful && response.body() != null) {
                val movieDTO = response.body()
                movieDTO?.let {
                    detailsLiveDataToObserve.value =
                        AppState.SuccessMovie(convertMovieDtoToMovie(it))
                }
            } else {
                detailsLiveDataToObserve.value = AppState.Error(response.code().toString())
            }
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            detailsLiveDataToObserve.value = AppState.Error(t.localizedMessage)
        }
    }

    fun saveDataToDB(movie:Movie) {
        historyRepositoryImpl.saveHistory(movie)
        historyRepositoryImpl.saveMovie(movie)
    }
}
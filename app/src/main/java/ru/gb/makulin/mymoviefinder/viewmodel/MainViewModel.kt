package ru.gb.makulin.mymoviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import ru.gb.makulin.mymoviefinder.facade.RemoteDataSource
import ru.gb.makulin.mymoviefinder.facade.main.MainRepositoryImpl
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListDTO


class MainViewModel(
    private val mainLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepositoryImpl: MainRepositoryImpl = MainRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = mainLiveDataToObserve

    fun getMoviesListFromRemote() {
        mainLiveDataToObserve.value = AppState.Loading
        getDataFromServer()
    }

    private fun getDataFromServer() {
        getTopRatedMoviesListFromServer()
        getNowPlayingMoviesListFromServer()
        getUpcomingMoviesListFromServer()
    }

    fun getTopRatedMoviesListFromServer() {
        mainRepositoryImpl.getTopRatedMoviesListFromServer(topRatedCallback)
    }

    fun getNowPlayingMoviesListFromServer() {
        mainRepositoryImpl.getNowPlayingMoviesListFromServer(nowPlayingCallback)
    }

    fun getUpcomingMoviesListFromServer() {
        mainRepositoryImpl.getUpcomingMoviesListFromServer(upcomingCallback)
    }

    private val topRatedCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            if (response.isSuccessful && response.body() != null) {
                mainLiveDataToObserve.value = AppState.SuccessTopRatedMovies(response.body()!!)
            } else {
                mainLiveDataToObserve.value =
                    AppState.ErrorTopRatedMovies(response.code().toString())
            }
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.ErrorTopRatedMovies(t.localizedMessage)
        }
    }

    private val nowPlayingCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            if (response.isSuccessful && response.body() != null) {
                mainLiveDataToObserve.value = AppState.SuccessNowPlayingMovies(response.body()!!)
            } else {
                mainLiveDataToObserve.value =
                    AppState.ErrorNowPlayingMovies(response.code().toString())
            }
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.ErrorNowPlayingMovies(t.localizedMessage)
        }
    }

    private val upcomingCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            if (response.isSuccessful && response.body() != null) {
                mainLiveDataToObserve.value = AppState.SuccessUpcomingMovies(response.body()!!)
            } else {
                mainLiveDataToObserve.value =
                    AppState.ErrorUpcomingMovies(response.code().toString())
            }
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.ErrorUpcomingMovies(t.localizedMessage)
        }
    }

}
package ru.gb.makulin.mymoviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import ru.gb.makulin.mymoviefinder.facade.MainRepositoryImpl
import ru.gb.makulin.mymoviefinder.facade.MoviesListDTO
import ru.gb.makulin.mymoviefinder.facade.RemoteDataSource


class MainViewModel(
    private val mainLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepositoryImpl: MainRepositoryImpl = MainRepositoryImpl(RemoteDataSource())
) : ViewModel() {


    //Костыль, который я не догадался как обойти
    private var moviesList = mutableListOf<MoviesListDTO>(
        MoviesListDTO(-1, listOf()),
        MoviesListDTO(-1, listOf()),
        MoviesListDTO(-1, listOf())
    )

    fun getLiveData(): LiveData<AppState> = mainLiveDataToObserve

    fun getMoviesListFromRemote() {
        mainLiveDataToObserve.value = AppState.Loading
        getDataFromServer()


    }

    private fun getDataFromServer() {
        mainRepositoryImpl.getTopRatedMoviesListFromServer(topRatedCallback)
        mainRepositoryImpl.getNowPlayingMoviesListFromServer(nowPlayingCallback)
        mainRepositoryImpl.getUpcomingMoviesListFromServer(upcomingCallback)
    }

    private val topRatedCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            onCallbackResponse(0, response)
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.Error(t.localizedMessage)
        }
    }

    private val nowPlayingCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            onCallbackResponse(1, response)
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.Error(t.localizedMessage)
        }
    }

    private val upcomingCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            onCallbackResponse(2, response)
            /*
            Добавил передачу данных через LiveData тут, т.к. запросы через ретрофит обрабатываются
            асинхронно через enqueue(), а данный запрос последний в очереди.

            Если получится, то переделаю код под запросы через Observable.zip()
             */
            mainLiveDataToObserve.value = AppState.Success(
                moviesList[0],
                moviesList[1],
                moviesList[2]
            )
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.Error(t.localizedMessage)
        }
    }

    private fun onCallbackResponse(index: Int, response: Response<MoviesListDTO>) {
        if (response.isSuccessful && response.body() != null) {
            moviesList[index] = response.body() as MoviesListDTO
        } else {
            mainLiveDataToObserve.value = AppState.Error(response.code().toString())
        }
    }
}
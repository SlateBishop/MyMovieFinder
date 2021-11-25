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

    companion object {
        private const val TOP_RATED_INDEX = 0
        private const val NOW_PLAYING_INDEX = 1
        private const val UPCOMING_INDEX = 2
    }


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
            onCallbackResponse(TOP_RATED_INDEX, response)
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.Error(t.localizedMessage)
        }
    }

    private val nowPlayingCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            onCallbackResponse(NOW_PLAYING_INDEX, response)
        }

        override fun onFailure(call: Call<MoviesListDTO>, t: Throwable) {
            mainLiveDataToObserve.value = AppState.Error(t.localizedMessage)
        }
    }

    private val upcomingCallback = object : retrofit2.Callback<MoviesListDTO> {
        override fun onResponse(call: Call<MoviesListDTO>, response: Response<MoviesListDTO>) {
            onCallbackResponse(UPCOMING_INDEX, response)
            /*
            Второй костыль.
            Добавил передачу данных через LiveData тут, т.к. запросы через ретрофит обрабатываются
            асинхронно через enqueue(), а данный запрос последний в очереди.

            Если получится, то переделаю код под запросы через Observable.zip()
            Upd не разобрался с RxJava2 с наскоку.
             */
            mainLiveDataToObserve.value = AppState.SuccessMoviesLists(
                moviesList[TOP_RATED_INDEX],
                moviesList[NOW_PLAYING_INDEX],
                moviesList[UPCOMING_INDEX]
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
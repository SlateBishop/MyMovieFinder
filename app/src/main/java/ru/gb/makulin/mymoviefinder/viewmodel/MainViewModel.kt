package ru.gb.makulin.mymoviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.makulin.mymoviefinder.facade.MoviesListDTO
import ru.gb.makulin.mymoviefinder.facade.MoviesListLoader
import ru.gb.makulin.mymoviefinder.facade.MoviesListLoaderListener


class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel(), MoviesListLoaderListener {

    companion object {
        private const val RU_LANG = "ru-RU"
        private const val EN_LANG = "en-US"
        private const val LOAD_ERROR = "Ошибка загрузки данных"
    }

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    fun getDataFromRemote() {
        liveDataToObserve.value = AppState.Loading
        MoviesListLoader(this).loadMovies()
    }

    override fun onLoaded(moviesDTO: List<MoviesListDTO>) {
        liveDataToObserve.value = AppState.Success(
            moviesDTO[0],
            moviesDTO[1],
            moviesDTO[2]
        )
    }

    override fun onFailed(throwable: Throwable) {
        liveDataToObserve.value = AppState.Error(RuntimeException(LOAD_ERROR))
    }
}
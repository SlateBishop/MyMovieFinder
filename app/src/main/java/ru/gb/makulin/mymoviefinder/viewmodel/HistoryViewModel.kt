package ru.gb.makulin.mymoviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import ru.gb.makulin.mymoviefinder.app.MyApp
import ru.gb.makulin.mymoviefinder.facade.RemoteDataSource
import ru.gb.makulin.mymoviefinder.facade.history.LocalRepository
import ru.gb.makulin.mymoviefinder.facade.history.LocalRepositoryImpl
import ru.gb.makulin.mymoviefinder.facade.main.MainRepositoryImpl
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListDTO
import ru.gb.makulin.mymoviefinder.utils.convertMoviesListDtoToMoviesList


class HistoryViewModel(
    private val historyLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val localRepositoryImpl: LocalRepository = LocalRepositoryImpl(MyApp.getHistoryDAO())
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = historyLiveDataToObserve

    fun getMoviesHistoryFromLocal() {
        historyLiveDataToObserve.value = AppState.Loading
        historyLiveDataToObserve.value = AppState.SuccessHistory(localRepositoryImpl.getAllHistory())
    }

// Корректно ли так поступить или мне следовало действовать через AppState?
    fun clearHistory() {
        localRepositoryImpl.clearHistory()
    }

}
package ru.gb.makulin.mymoviefinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.makulin.mymoviefinder.model.getNewMovies
import ru.gb.makulin.mymoviefinder.model.getTopRatedMovies
import ru.gb.makulin.mymoviefinder.model.getUpcomingMovies
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()): ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    fun getDataFromRemote() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
//            liveDataToObserve.value =AppState.Success(getTopRatedMovies(),getNewMovies(),getUpcomingMovies())
            liveDataToObserve.postValue(AppState.Success(getTopRatedMovies(),getNewMovies(),getUpcomingMovies()))
        }.start()
    }



}
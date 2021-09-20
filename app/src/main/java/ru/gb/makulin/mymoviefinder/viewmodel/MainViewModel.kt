package ru.gb.makulin.mymoviefinder.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.makulin.mymoviefinder.facade.RepositoryImpl
import java.lang.Thread.sleep


class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    fun getDataFromRemote() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(2000)
            Handler(Looper.getMainLooper()).post {
                liveDataToObserve.value = AppState.Success(
                    repositoryImpl.getTopRatedMoviesFromRemoteSource(),
                    repositoryImpl.getNewMoviesFromRemoteSource(),
                    repositoryImpl.getUpcomingMoviesFromRemoteSource()
                )
            }
        }.start()
    }
}
package ru.gb.makulin.mymoviefinder.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.makulin.mymoviefinder.facade.*
import java.lang.Thread.sleep


class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel(), MoviesListLoaderListener {

    companion object {
        private const val TOP_RATED_TYPE = "top_rated"
        private const val UPCOMING_TYPE = "upcoming"
        private const val LATEST_TYPE = "latest"
        private const val RU_LANG = "ru-RU"
        private const val EN_LANG = "en-US"
    }
    private val topRatedPage = 1
    private val upcomingPage = 1
    private val latestPage = 1
    private lateinit var topRatedMovies: MoviesListDTO //FIXME
    private lateinit var upcomingMovies: MoviesListDTO //FIXME
    private lateinit var latestMovies: MoviesListDTO //FIXME

    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    fun getDataFromRemote() {
        liveDataToObserve.value = AppState.Loading
//        MoviesListLoader(this).loadMovies(TOP_RATED_TYPE, topRatedPage, RU_LANG)
//        MoviesListLoader(this).loadMovies(UPCOMING_TYPE, upcomingPage, RU_LANG)
//        MoviesListLoader(this).loadMovies(LATEST_TYPE, latestPage, RU_LANG)
//        liveDataToObserve.value = AppState.Success(
//            topRatedMovies,
//            latestMovies,
//            upcomingMovies
//        )


        Thread {
            sleep(1000)
            Handler(Looper.getMainLooper()).post {
                liveDataToObserve.value = AppState.Success(
                    repositoryImpl.getTopRatedMoviesFromRemoteSource(),
                    repositoryImpl.getNewMoviesFromRemoteSource(),
                    repositoryImpl.getUpcomingMoviesFromRemoteSource()
                )
//                liveDataToObserve.value = AppState.Error(RuntimeException("bla bla"))
            }
        }.start()
    }

    override fun onLoaded(moviesDTO: MoviesListDTO, listType: String) {
        when(listType) {
            TOP_RATED_TYPE -> topRatedMovies = moviesDTO
            UPCOMING_TYPE -> upcomingMovies = moviesDTO
            LATEST_TYPE -> latestMovies = moviesDTO
        }
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}
package ru.gb.makulin.mymoviefinder.viewmodel

import ru.gb.makulin.mymoviefinder.model.Movie

sealed class AppState {
    object Loading : AppState()
    data class Success(
        val topRatedData: List<Movie>,
        val newData: List<Movie>,
        val upcomingData: List<Movie>
    ) : AppState()  //FIXME разделить модель и вьюмодел

    data class Error(val error: Throwable) : AppState()
}

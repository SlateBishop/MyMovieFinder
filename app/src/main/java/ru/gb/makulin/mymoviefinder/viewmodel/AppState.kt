package ru.gb.makulin.mymoviefinder.viewmodel

import ru.gb.makulin.mymoviefinder.facade.MoviesListDTO

sealed class AppState {
    object Loading : AppState()
    data class Success(
        val topRatedData: MoviesListDTO,
        val newData: MoviesListDTO,
        val upcomingData: MoviesListDTO
    ) : AppState()

    data class Error(val error: Throwable) : AppState()
}

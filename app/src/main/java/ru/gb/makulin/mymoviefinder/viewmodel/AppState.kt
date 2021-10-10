package ru.gb.makulin.mymoviefinder.viewmodel

import ru.gb.makulin.mymoviefinder.facade.MoviesListDTO
import ru.gb.makulin.mymoviefinder.facade.MoviesListResultDTO
import ru.gb.makulin.mymoviefinder.model.Movie

sealed class AppState {
    object Loading : AppState()
    data class Success(
        val topRatedData: MoviesListDTO,
        val newData: MoviesListDTO,
        val upcomingData: MoviesListDTO
//        val topRatedData: List<MoviesListResultDTO>,
//        val newData: List<MoviesListResultDTO>,
//        val upcomingData: List<MoviesListResultDTO>
    ) : AppState()  //FIXME разделить модель и вьюмодел

    data class Error(val error: Throwable) : AppState()
}

package ru.gb.makulin.mymoviefinder.viewmodel

import ru.gb.makulin.mymoviefinder.facade.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.MoviesListDTO

sealed class AppState {
    object Loading : AppState()
    data class SuccessMoviesLists(
        val topRatedData: MoviesListDTO,
        val newData: MoviesListDTO,
        val upcomingData: MoviesListDTO
    ) : AppState()

    data class SuccessMovie(val movieDTO: MovieDTO) : AppState()
    data class Error(val errorCode: String) : AppState()
}

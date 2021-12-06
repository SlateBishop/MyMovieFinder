package ru.gb.makulin.mymoviefinder.viewmodel

import ru.gb.makulin.mymoviefinder.facade.details.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListDTO

sealed class AppState {
    object Loading : AppState()
    data class SuccessTopRatedMovies(val topRatedData: MoviesListDTO) : AppState()
    data class SuccessNowPlayingMovies(val nowPlayingData: MoviesListDTO) : AppState()
    data class SuccessUpcomingMovies(val upcomingData: MoviesListDTO) : AppState()

    data class ErrorTopRatedMovies(val errorCode: String) : AppState()
    data class ErrorNowPlayingMovies(val errorCode: String) : AppState()
    data class ErrorUpcomingMovies(val errorCode: String) : AppState()

    data class SuccessMovie(val movieDTO: MovieDTO) : AppState()
    data class Error(val errorCode: String) : AppState()
}

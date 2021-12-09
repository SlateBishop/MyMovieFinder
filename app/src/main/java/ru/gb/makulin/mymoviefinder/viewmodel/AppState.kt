package ru.gb.makulin.mymoviefinder.viewmodel

import ru.gb.makulin.mymoviefinder.model.HistoryMovieData
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.model.MoviesList

sealed class AppState {
    object Loading : AppState()
    data class SuccessTopRatedMovies(val topRatedData: MoviesList) : AppState()
    data class SuccessNowPlayingMovies(val nowPlayingData: MoviesList) : AppState()
    data class SuccessUpcomingMovies(val upcomingData: MoviesList) : AppState()

    data class SuccessHistory(val historyMovie: List<HistoryMovieData>) : AppState()

    data class ErrorTopRatedMovies(val errorCode: String) : AppState()
    data class ErrorNowPlayingMovies(val errorCode: String) : AppState()
    data class ErrorUpcomingMovies(val errorCode: String) : AppState()

    data class SuccessMovie(val movie: Movie) : AppState()
    data class Error(val errorCode: String) : AppState()
}

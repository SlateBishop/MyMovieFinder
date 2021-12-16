package ru.gb.makulin.mymoviefinder.facade.history

import ru.gb.makulin.mymoviefinder.model.HistoryMovieData
import ru.gb.makulin.mymoviefinder.model.Movie

interface LocalRepository {
    fun getAllHistory():List<HistoryMovieData>
    fun clearHistory()
    fun saveHistory(movie: Movie)
    fun saveMovie(movie: Movie)
    fun updateHistory()
}
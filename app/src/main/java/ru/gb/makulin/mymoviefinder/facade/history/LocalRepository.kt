package ru.gb.makulin.mymoviefinder.facade.history

import ru.gb.makulin.mymoviefinder.model.HistoryMovieData

interface LocalRepository {
    fun getAllHistory():List<HistoryMovieData>
    fun clearHistory()
    fun saveHistory(movie: HistoryMovieData)
    fun saveMovie(movie: HistoryMovieData)
    fun updateHistory()
}
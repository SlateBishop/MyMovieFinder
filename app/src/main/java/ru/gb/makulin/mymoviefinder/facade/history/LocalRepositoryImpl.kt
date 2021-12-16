package ru.gb.makulin.mymoviefinder.facade.history

import ru.gb.makulin.mymoviefinder.model.HistoryMovieData
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.room.HistoryDAO
import ru.gb.makulin.mymoviefinder.utils.convertMovieToHistoryEntity
import ru.gb.makulin.mymoviefinder.utils.convertMovieToMovieEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDAO) : LocalRepository {
    override fun getAllHistory(): List<HistoryMovieData> {
        return localDataSource.getAllHistoryMovieData()
    }

    override fun clearHistory() {
        localDataSource.clearHistory()
    }

    override fun saveHistory(movie: Movie) {
        localDataSource.insertHistory(convertMovieToHistoryEntity(movie))
    }

    override fun saveMovie(movie: Movie) {
        localDataSource.insertMovie(convertMovieToMovieEntity(movie))
    }

    override fun updateHistory() {
        TODO("Not yet implemented")
    }
}
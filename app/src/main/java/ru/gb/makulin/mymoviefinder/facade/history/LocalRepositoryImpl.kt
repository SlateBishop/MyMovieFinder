package ru.gb.makulin.mymoviefinder.facade.history

import ru.gb.makulin.mymoviefinder.model.HistoryMovieData
import ru.gb.makulin.mymoviefinder.room.HistoryDAO
import ru.gb.makulin.mymoviefinder.utils.convertHistoryDataToHistoryEntity
import ru.gb.makulin.mymoviefinder.utils.convertHistoryDataToMovieEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDAO) : LocalRepository {
    override fun getAllHistory(): List<HistoryMovieData> {
        return localDataSource.getAllHistoryMovieData()
    }

    override fun clearHistory() {
        localDataSource.clearHistory()
    }

    override fun saveHistory(movie: HistoryMovieData) {
        localDataSource.insertHistory(convertHistoryDataToHistoryEntity(movie))
    }

    override fun saveMovie(movie: HistoryMovieData) {
        localDataSource.insertMovie(convertHistoryDataToMovieEntity(movie))
    }

    override fun updateHistory() {
        TODO("Not yet implemented")
    }
}
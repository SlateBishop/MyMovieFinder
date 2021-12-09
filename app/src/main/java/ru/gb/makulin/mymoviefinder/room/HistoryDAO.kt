package ru.gb.makulin.mymoviefinder.room

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface HistoryDAO {

    @Insert(onConflict = REPLACE)
    fun insertHistory(entity: HistoryEntity)

    @Insert(onConflict = IGNORE)
    fun insertMovie(entity: MovieEntity)

    @Update
    fun updateHistory(entity: HistoryEntity)

    @Delete
    fun deleteHistory(entity: HistoryEntity)

    @Query("DELETE from HistoryEntity")
    fun clearHistory()

    @Query("SELECT * from HistoryEntity")
    fun getAllHistory(): List<HistoryEntity>

    @Query(
        "SELECT MovieEntity.name, MovieEntity.description, MovieEntity.poster_path, " +
                "HistoryEntity.date, HistoryEntity.comment FROM HistoryEntity,MovieEntity " +
                "WHERE HistoryEntity.id =:id"
    )
    fun getHistoryMovieData(id:Long) :HistoryMovieData

}
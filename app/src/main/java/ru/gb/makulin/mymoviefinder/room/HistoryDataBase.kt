package ru.gb.makulin.mymoviefinder.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.gb.makulin.mymoviefinder.utils.Converters

@Database(entities = [HistoryEntity::class, MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDAO(): HistoryDAO
}
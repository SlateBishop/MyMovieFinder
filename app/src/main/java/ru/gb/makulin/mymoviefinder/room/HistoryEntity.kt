package ru.gb.makulin.mymoviefinder.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    val date: Date,
    var comment: String = ""
)

package ru.gb.makulin.mymoviefinder.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    @ColumnInfo(name = "poster_path")
    val posterPath:String
)

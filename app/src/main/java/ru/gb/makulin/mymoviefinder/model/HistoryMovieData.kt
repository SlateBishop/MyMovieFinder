package ru.gb.makulin.mymoviefinder.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class HistoryMovieData(
    @ColumnInfo(name = "movie_id")
    val movieID: Long,
    val name: String,
    val description: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    val date: Date,
    var comment: String = ""
) : Parcelable

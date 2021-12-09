package ru.gb.makulin.mymoviefinder.room

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryMovieData(
    val name: String,
    val description: String,
    val posterPath: String,
    val date: String,
    var comment: String = ""
) : Parcelable

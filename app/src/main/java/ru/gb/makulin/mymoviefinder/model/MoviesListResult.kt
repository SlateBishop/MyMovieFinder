package ru.gb.makulin.mymoviefinder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesListResult(
    val adult: Boolean,
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) : Parcelable

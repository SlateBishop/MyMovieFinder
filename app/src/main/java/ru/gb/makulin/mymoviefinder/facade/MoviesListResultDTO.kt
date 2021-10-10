package ru.gb.makulin.mymoviefinder.facade

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesListResultDTO(
    val adult: Boolean,
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
) : Parcelable

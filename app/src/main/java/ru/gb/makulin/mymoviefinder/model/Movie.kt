package ru.gb.makulin.mymoviefinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    val adult: Boolean,
    val genres: List<Genre>,
    val id: Int,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable
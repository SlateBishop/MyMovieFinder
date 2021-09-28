package ru.gb.makulin.mymoviefinder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String = "Test name",
    val releaseData: String = "Test year",
    val ratio: Double = -10.1,
    val genres: List<String> = listOf("комедия", "драма"),
    val duration: Int = -20,
    val description: String = "Very pretty description"
) : Parcelable



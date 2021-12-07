package ru.gb.makulin.mymoviefinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val name: String
) : Parcelable {
    override fun toString(): String {
        return name
    }
}

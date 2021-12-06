package ru.gb.makulin.mymoviefinder.facade.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreDTO(
    val name: String
) : Parcelable {
    override fun toString(): String {
        return name
    }
}
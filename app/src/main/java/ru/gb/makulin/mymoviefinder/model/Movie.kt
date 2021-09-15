package ru.gb.makulin.mymoviefinder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String = "Test name",
    val year: String = "Test year",     //TODO или лучше было бы использовать Int?
    val ratio: Double = -10.1
) : Parcelable

//TODO убрать временную заглушку
fun getTopRatedMovies(): List<Movie> {
    return listOf(
        Movie("1", "2001", 6.1),
        Movie("2", "2002", 6.2),
        Movie("3", "2003", 6.3),
        Movie("4", "2004", 6.4),
        Movie("5", "2005", 6.5),
        Movie("6", "2006", 6.6),
        Movie("7", "2007", 6.7),
        Movie("8", "2008", 6.8),
    )
}

//TODO убрать временную заглушку
fun getNewMovies(): List<Movie> {
    return listOf(
        Movie("8", "2008", 6.8),
        Movie("7", "2007", 6.7),
        Movie("6", "2006", 6.6),
        Movie("5", "2005", 6.5),
        Movie("4", "2004", 6.4),
        Movie("3", "2003", 6.3),
        Movie("2", "2002", 6.2),
        Movie("1", "2001", 6.1),
    )
}

//TODO убрать временную заглушку
fun getUpcomingMovies(): List<Movie> {
    return listOf(
        Movie("5", "2005", 6.5),
        Movie("6", "2006", 6.6),
        Movie("7", "2007", 6.7),
        Movie("8", "2008", 6.8),
        Movie("1", "2001", 6.1),
        Movie("2", "2002", 6.2),
        Movie("3", "2003", 6.3),
        Movie("4", "2004", 6.4),
    )
}

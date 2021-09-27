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

//TODO убрать временную заглушку
fun getTopRatedMovies(): List<Movie> {
    return listOf(
        Movie(
            "1",
            "28/11/2001",
            6.1,
            listOf("фантастика", "триллер"),
            155,
            "Здраствуйте. Я, Кирилл. Хотел бы чтобы вы сделали игру, 3Д-экшон суть такова... Пользователь может играть лесными эльфами, охраной дворца и злодеем. И если пользователь играет эльфами то эльфы в лесу, домики деревяные набигают солдаты дворца и злодеи. Можно грабить корованы... И эльфу раз лесные то сделать так что там густой лес..."
        ),
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

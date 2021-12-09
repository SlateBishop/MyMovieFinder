package ru.gb.makulin.mymoviefinder.utils

import androidx.room.TypeConverter
import ru.gb.makulin.mymoviefinder.facade.details.GenreDTO
import ru.gb.makulin.mymoviefinder.facade.details.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListDTO
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListResultDTO
import ru.gb.makulin.mymoviefinder.model.*
import ru.gb.makulin.mymoviefinder.room.HistoryEntity
import ru.gb.makulin.mymoviefinder.room.MovieEntity
import java.util.*


fun convertMovieDtoToMovie(movieDTO: MovieDTO): Movie {
    return with(movieDTO) {
        Movie(
            adult,
            convertGenresDtoToGenres(genres),
            id,
            original_title,
            title,
            overview,
            popularity,
            poster_path,
            release_date,
            runtime,
            tagline,
            vote_average,
            vote_count,
        )
    }
}

fun convertGenresDtoToGenres(genresDTO: List<GenreDTO>): List<Genre> {
    return genresDTO.map {
        Genre(it.name)
    }
}

fun convertMoviesListDtoToMoviesList(listDTO: MoviesListDTO): MoviesList {
    return MoviesList(
        listDTO.page,
        convertMoviesListResultDtoToMoviesListResult(listDTO.results)
    )
}

fun convertMoviesListResultDtoToMoviesListResult(resultDTO: List<MoviesListResultDTO>): MutableList<MoviesListResult> {
    return resultDTO.map {
        with(it) {
            MoviesListResult(
                adult,
                id,
                poster_path,
                release_date,
                title,
                vote_average
            )
        }
    }.toMutableList()
}

fun convertHistoryDataToHistoryEntity(data: HistoryMovieData): HistoryEntity {
    return with(data) {
        HistoryEntity(0, movieID, date, comment)
    }
}

fun convertHistoryDataToMovieEntity(data: HistoryMovieData): MovieEntity {
    return with(data) {
        MovieEntity(movieID,name, description, posterPath)
    }
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}












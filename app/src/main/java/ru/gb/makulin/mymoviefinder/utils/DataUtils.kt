package ru.gb.makulin.mymoviefinder.utils

import ru.gb.makulin.mymoviefinder.facade.details.GenreDTO
import ru.gb.makulin.mymoviefinder.facade.details.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListDTO
import ru.gb.makulin.mymoviefinder.facade.main.MoviesListResultDTO
import ru.gb.makulin.mymoviefinder.model.Genre
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.model.MoviesList
import ru.gb.makulin.mymoviefinder.model.MoviesListResult

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

fun convertMoviesListDtoToMoviesList (listDTO: MoviesListDTO) : MoviesList {
    return MoviesList(
        listDTO.page,
        convertMoviesListResultDtoToMoviesListResult(listDTO.results)
    )
}

fun convertMoviesListResultDtoToMoviesListResult (resultDTO: List<MoviesListResultDTO>) : List<MoviesListResult> {
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
    }






}

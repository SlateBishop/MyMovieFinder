package ru.gb.makulin.mymoviefinder.facade

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.gb.makulin.mymoviefinder.utils.*

interface MovieApi {

    @GET(MOVIE_API_URL_END_POINT_TOP_RATED)
    fun getTopRatedMovies(
        @Query(MOVIE_API_KEY_NAME) apikey: String,
        @Query(MOVIE_API_PAGE_NAME) page: Int = 1,
        @Query(MOVIE_API_LANGUAGE_NAME) lang: String = "ru-RU"
    ): Call<List<MoviesListDTO>>

    @GET(MOVIE_API_URL_END_POINT_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(MOVIE_API_KEY_NAME) apikey: String,
        @Query(MOVIE_API_PAGE_NAME) page: Int = 1,
        @Query(MOVIE_API_LANGUAGE_NAME) lang: String = "ru-RU"
    ): Call<List<MoviesListDTO>>


    @GET(MOVIE_API_URL_END_POINT_UPCOMING)
    fun getUpcomingMovies(
        @Query(MOVIE_API_KEY_NAME) apikey: String,
        @Query(MOVIE_API_PAGE_NAME) page: Int = 1,
        @Query(MOVIE_API_LANGUAGE_NAME) lang: String = "ru-RU"
    ): Call<List<MoviesListDTO>>

    @GET(MOVIE_API_URL_END_POINT_DETAILS)
    fun getMovieDetails(
        @Path(MOVIE_API_URL_ID_PATH_DETAILS) movieId: Int,
        @Query(MOVIE_API_KEY_NAME) apikey: String,
        @Query(MOVIE_API_LANGUAGE_NAME) lang: String = "ru-RU"
    ): Call<MovieDTO>
}
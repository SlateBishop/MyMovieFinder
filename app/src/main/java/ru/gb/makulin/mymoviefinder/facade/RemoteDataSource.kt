package ru.gb.makulin.mymoviefinder.facade

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.makulin.mymoviefinder.BuildConfig
import ru.gb.makulin.mymoviefinder.utils.BASE_MOVIE_API_URL

class RemoteDataSource {

    private val movieApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_MOVIE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(MovieApi::class.java)
    }

    fun getTopRatedMoviesList(
        callback: retrofit2.Callback<List<MoviesListDTO>>,
        page: Int = 1,
        lang: String = "ru-RU"
    ) {
        movieApi.getTopRatedMovies(BuildConfig.MOVIE_API_KEY, page, lang).enqueue(callback)
    }

    fun getNowPlayingMoviesList(
        callback: retrofit2.Callback<List<MoviesListDTO>>,
        page: Int = 1,
        lang: String = "ru-RU"
    ) {
        movieApi.getNowPlayingMovies(BuildConfig.MOVIE_API_KEY, page, lang).enqueue(callback)
    }

    fun getUpcomingMoviesList(
        callback: retrofit2.Callback<List<MoviesListDTO>>,
        page: Int = 1,
        lang: String = "ru-RU"
    ) {
        movieApi.getUpcomingMovies(BuildConfig.MOVIE_API_KEY, page, lang).enqueue(callback)
    }

    fun getMovieDetails(
        callback: retrofit2.Callback<MovieDTO>,
        movieId: Int,
        lang: String = "ru-RU"
    ) {
        movieApi.getMovieDetails(movieId, BuildConfig.MOVIE_API_KEY, lang).enqueue(callback)
    }
}
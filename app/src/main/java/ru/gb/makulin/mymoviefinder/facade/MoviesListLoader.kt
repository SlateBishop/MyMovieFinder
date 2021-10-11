package ru.gb.makulin.mymoviefinder.facade

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import ru.gb.makulin.mymoviefinder.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MoviesListLoader(private val listener: MoviesListLoaderListener) {
    /*
    Не придумал ничего умнее, чем сделать тупой толстый лоадер
     */

    private var moviesList: MutableList<MoviesListDTO> = mutableListOf()
    private val topRatedUrl =
        URL("https://api.themoviedb.org/3/movie/top_rated?api_key=${BuildConfig.MOVIE_API_KEY}&page=1&language=ru-RU")
    private val upcomingUrl =
        URL("https://api.themoviedb.org/3/movie/upcoming?api_key=${BuildConfig.MOVIE_API_KEY}&language=ru-RU")
    private val nowPlayingUrl =
        URL("https://api.themoviedb.org/3/movie/now_playing?api_key=${BuildConfig.MOVIE_API_KEY}&page=1&language=ru-RU")

    fun loadMovies() {
        Thread {
            loadList(topRatedUrl)
            loadList(nowPlayingUrl)
            loadList(upcomingUrl)
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onLoaded(moviesList) }
        }.start()
    }

    private fun loadList(url: URL) {
        val urlConnection by lazy {
            url.openConnection() as HttpsURLConnection
        }
        try {
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val moviesDTO = Gson().fromJson(reader, MoviesListDTO::class.java)
            moviesList.add(moviesDTO)
        } catch (e: RuntimeException) {
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onFailed(e) }
        } finally {
            urlConnection.disconnect()
        }
    }
}
package ru.gb.makulin.mymoviefinder.facade

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import ru.gb.makulin.mymoviefinder.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class TopRatedMoviesLoader(private val listener: TopRatedLoaderListener) {

    fun loadMovies(page:Long = 1, language:String = "ru-RU") {
        val url = URL("https://api.themoviedb.org/3/movie/top_rated?api_key=${BuildConfig.MOVIE_API_KEY}&page=${page}&language=${language}")

        Thread{
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod ="GET"
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val moviesDTO =Gson().fromJson(reader,TopRatedMoviesDTO::class.java)
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onLoaded(moviesDTO) }
            urlConnection.disconnect()
        }.start()
    }
}
package ru.gb.makulin.mymoviefinder.view.details

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import ru.gb.makulin.mymoviefinder.BuildConfig
import ru.gb.makulin.mymoviefinder.facade.MovieDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val ID_EXTRA = "id extra"
const val LANG_EXTRA = "lang extra"
const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"

class DetailsService(name: String = "DetailsService") : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val id = it.getIntExtra(ID_EXTRA, -1)
            val lang = it.getStringExtra(LANG_EXTRA)
            if (lang != null) {
                loadMovie(id, lang)
            } else {
                loadMovie(id)
            }
        }
    }

    private fun loadMovie(id: Int = 680, language: String = "ru-RU") {
        val url =
            URL("https://api.themoviedb.org/3/movie/${id}?api_key=${BuildConfig.MOVIE_API_KEY}&language=${language}")
        val urlConnection by lazy {
            url.openConnection() as HttpsURLConnection
        }
        try {
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val movieDTO = Gson().fromJson(reader, MovieDTO::class.java)
            val intent = Intent(DETAILS_INTENT_FILTER)
            intent.putExtra(DETAILS_LOAD_RESULT_EXTRA, movieDTO)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        } finally {
            urlConnection.disconnect()
        }
    }
}
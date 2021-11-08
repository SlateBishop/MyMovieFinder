package ru.gb.makulin.mymoviefinder.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import ru.gb.makulin.mymoviefinder.R


class ConnectivityBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && context != null) {
            val connectivityState =
                intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (connectivityState) {
                onConnectivityStateLost(context)
            } else {
                onConnectivityStateFound(context)
            }
        }
    }

    /*
    В классе BroadcastReceiver(), который имеет свой контекст, у меня почему то не сразботало
    при создании логов и тостов использовать this. Пришлось передавать контекст в методы, а в
    методе onReceive отдельно проверять контекст на null.
    Не смог разобраться почему так..
     */
    private fun onConnectivityStateLost(context: Context) {
        Log.d(context.getString(R.string.LogTAG), context.getString(R.string.connection_lost_msg))
        Toast.makeText(context, context.getString(R.string.connection_lost_msg), Toast.LENGTH_LONG)
            .show()
    }

    private fun onConnectivityStateFound(context: Context) {
        Log.d(context.getString(R.string.LogTAG), context.getString(R.string.connection_found_msg))
        Toast.makeText(context, context.getString(R.string.connection_found_msg), Toast.LENGTH_LONG)
            .show()
    }

}
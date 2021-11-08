package ru.gb.makulin.mymoviefinder.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ConnectivityBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.action
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            Log.d("mylogs", message)
        }
    }
}
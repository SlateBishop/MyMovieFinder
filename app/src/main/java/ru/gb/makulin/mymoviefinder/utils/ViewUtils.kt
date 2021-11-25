package ru.gb.makulin.mymoviefinder.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackbar(text: String, actionText: String, action: View.OnClickListener) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).setAction(actionText, action).show()
}
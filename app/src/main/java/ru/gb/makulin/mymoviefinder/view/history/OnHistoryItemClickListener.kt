package ru.gb.makulin.mymoviefinder.view.history

import ru.gb.makulin.mymoviefinder.model.HistoryMovieData

interface OnHistoryItemClickListener {
    fun onItemClick(movie: HistoryMovieData)
}
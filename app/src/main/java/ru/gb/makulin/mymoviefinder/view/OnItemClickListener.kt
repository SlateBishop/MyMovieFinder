package ru.gb.makulin.mymoviefinder.view

import ru.gb.makulin.mymoviefinder.model.Movie

interface OnItemClickListener {
    fun onItemClick(movie: Movie)
}
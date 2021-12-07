package ru.gb.makulin.mymoviefinder.view.main

import ru.gb.makulin.mymoviefinder.model.MoviesListResult

interface OnItemClickListener {
    fun onItemClick(movie: MoviesListResult)
}
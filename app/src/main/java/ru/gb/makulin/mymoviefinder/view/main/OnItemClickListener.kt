package ru.gb.makulin.mymoviefinder.view.main

import ru.gb.makulin.mymoviefinder.facade.MoviesListResultDTO
import ru.gb.makulin.mymoviefinder.model.Movie

interface OnItemClickListener {
    fun onItemClick(movie: MoviesListResultDTO)
}
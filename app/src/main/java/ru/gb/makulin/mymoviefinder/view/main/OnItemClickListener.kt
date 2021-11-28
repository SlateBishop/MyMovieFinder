package ru.gb.makulin.mymoviefinder.view.main

import ru.gb.makulin.mymoviefinder.facade.main.MoviesListResultDTO

interface OnItemClickListener {
    fun onItemClick(movie: MoviesListResultDTO)
}
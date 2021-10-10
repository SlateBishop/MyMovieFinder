package ru.gb.makulin.mymoviefinder.facade

data class GenreDTO(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

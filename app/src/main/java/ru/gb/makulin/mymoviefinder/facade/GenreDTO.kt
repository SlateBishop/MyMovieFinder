package ru.gb.makulin.mymoviefinder.facade

data class GenreDTO(
//    val id: Long,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

package ru.gb.makulin.mymoviefinder.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.MoviesListItemBinding
import ru.gb.makulin.mymoviefinder.model.Movie

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainFragmentHolder>() {

    var moviesData: List<Movie> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MainFragmentHolder {
        val binding = MoviesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainFragmentHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainFragmentHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    inner class MainFragmentHolder(private val binding: MoviesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) { //FIXME избежать связи с моделью!!!
            binding.apply {
                cardPoster.setImageResource(R.drawable.ic_launcher_background)
                cardTitle.text = movie.name
                cardYear.text = movie.year
                cardRating.text = movie.ratio.toString()
            }
        }

    }
}
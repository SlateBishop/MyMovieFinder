package ru.gb.makulin.mymoviefinder.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.makulin.mymoviefinder.databinding.MoviesListItemBinding
import ru.gb.makulin.mymoviefinder.model.MoviesListResult
import ru.gb.makulin.mymoviefinder.utils.POSTER_BASE_URL

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainFragmentHolder>() {

    private var moviesData: List<MoviesListResult> = listOf()
    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setData(data: List<MoviesListResult>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentHolder {
        val binding = MoviesListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainFragmentHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFragmentHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    inner class MainFragmentHolder(private val binding: MoviesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MoviesListResult) {
            binding.apply {
                cardPoster.load(POSTER_BASE_URL + movie.posterPath)
                cardTitle.text = movie.title
                cardYear.text = movie.releaseDate
                cardRating.text = movie.voteAverage.toString()
                root.setOnClickListener {
                    listener.onItemClick(movie)
                }
            }
        }
    }
}

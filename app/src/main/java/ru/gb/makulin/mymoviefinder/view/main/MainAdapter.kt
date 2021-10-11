package ru.gb.makulin.mymoviefinder.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.MoviesListItemBinding
import ru.gb.makulin.mymoviefinder.facade.MoviesListResultDTO

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainFragmentHolder>() {

    private var moviesData: List<MoviesListResultDTO> = listOf()
    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setData(data: List<MoviesListResultDTO>) {
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

        fun bind(movie: MoviesListResultDTO) {
            binding.apply {
                cardPoster.setImageResource(R.drawable.ic_launcher_background) //FIXME later
                cardTitle.text = movie.title
                cardYear.text = movie.release_date
                cardRating.text = movie.vote_average.toString()
                root.setOnClickListener {
                    listener.onItemClick(movie)
                }
            }
        }
    }
}

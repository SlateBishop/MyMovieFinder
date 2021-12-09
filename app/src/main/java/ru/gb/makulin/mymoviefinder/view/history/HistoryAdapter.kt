package ru.gb.makulin.mymoviefinder.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gb.makulin.mymoviefinder.databinding.HistoryListItemBinding
import ru.gb.makulin.mymoviefinder.model.HistoryMovieData
import ru.gb.makulin.mymoviefinder.utils.POSTER_BASE_URL

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryFragmentHolder>() {

    private var moviesData: List<HistoryMovieData> = listOf()
    private lateinit var listener: OnHistoryItemClickListener

    fun setOnItemClickListener(listener: OnHistoryItemClickListener) {
        this.listener = listener
    }

    fun setData(data: List<HistoryMovieData>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryFragmentHolder {
        val binding = HistoryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryFragmentHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryFragmentHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    inner class HistoryFragmentHolder(private val binding: HistoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: HistoryMovieData) {
            binding.apply {
                cardHistoryPoster.load(POSTER_BASE_URL + movie.posterPath)
                cardHistoryTitle.text = movie.name
                cardHistoryDate.text = movie.date.toString()
                root.setOnClickListener {
                    listener.onItemClick(movie)
                }
            }
        }
    }
}

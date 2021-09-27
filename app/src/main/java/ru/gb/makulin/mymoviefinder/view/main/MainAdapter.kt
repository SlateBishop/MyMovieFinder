package ru.gb.makulin.mymoviefinder.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.MoviesListItemBinding
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.view.OnItemClickListener

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainFragmentHolder>() {

    private var moviesData: List<Movie> = listOf()
    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setData(data: List<Movie>) {
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
        ) //TODO будет ли здесь утечка пямяти?
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

        fun bind(movie: Movie) { //FIXME в будущем избежать прямой связи с моделью!!!
            binding.apply {
                cardPoster.setImageResource(R.drawable.ic_launcher_background)
                cardTitle.text = movie.name
                cardYear.text = movie.releaseData
                cardRating.text = movie.ratio.toString()
                root.setOnClickListener {
                    listener.onItemClick(movie)
                }


/*
TODO а почему не стоит делать напрямую так, а пробрасывали на уроке каллбэки?
При таком подходе у меня по клику DetailsFragment открывается, но потом приложение виснет...
UPD приложение висло из-за глюка AVD, а не самого приложения.
                root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable(DetailsFragment.BUNDLE_KEY, movie)
                    (it.context as FragmentActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DetailsFragment.newInstance(bundle))
                        .addToBackStack("")
                        .commit()
                }
*/
            }
        }
    }

}

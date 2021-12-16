package ru.gb.makulin.mymoviefinder.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentDetailsBinding
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.model.MoviesListResult
import ru.gb.makulin.mymoviefinder.utils.POSTER_BASE_URL
import ru.gb.makulin.mymoviefinder.utils.makeSnackbar
import ru.gb.makulin.mymoviefinder.viewmodel.AppState
import ru.gb.makulin.mymoviefinder.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val BUNDLE_KEY = "movie_key"
    }

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private lateinit var movie: MoviesListResult

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movie = it.getParcelable<MoviesListResult>(BUNDLE_KEY)!!
            observeOnViewModel()
            getMovie(movie)
        }
    }

    private fun getMovie(movie: MoviesListResult) {
        viewModel.getMovieFromRemote(movie.id)
    }

    private fun observeOnViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loading.loading.visibility = View.GONE
                binding.root.makeSnackbar(
                    getString(R.string.onFailedDataLoadingText),
                    getString(R.string.snackActionText)
                ) {
                    getMovie(movie)
                }
            }
            AppState.Loading -> binding.loading.loading.visibility = View.VISIBLE
            is AppState.SuccessMovie -> {
                val movie = appState.movie
                viewModel.saveDataToDB(movie)
                setData(movie)
                binding.loading.loading.visibility = View.GONE
            }
        }
    }

    private fun setData(movie: Movie) {
        binding.apply {
            with(movie) {
                filmDetailName.text = title
                filmDetailGenres.text = genres.toString()
                filmDetailDuration.text = runtime.toString()
                filmDetailRating.text = voteAverage.toString()
                filmDetailDescription.text = overview
                filmDetailReleaseDate.text = releaseDate
                filmDetailImage.load(POSTER_BASE_URL + movie.posterPath)
            }
        }
    }
}
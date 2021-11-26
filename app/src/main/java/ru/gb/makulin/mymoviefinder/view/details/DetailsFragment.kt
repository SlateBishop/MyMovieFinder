package ru.gb.makulin.mymoviefinder.view.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.snackbar.Snackbar
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentDetailsBinding
import ru.gb.makulin.mymoviefinder.facade.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.MoviesListResultDTO
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

    private lateinit var movie: MoviesListResultDTO

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
            movie = it.getParcelable<MoviesListResultDTO>(BUNDLE_KEY)!!
            observeOnViewModel()
            getMovie(movie)
        }
    }

    private fun getMovie(movie: MoviesListResultDTO) {
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
                binding.loading.visibility = View.GONE
                binding.root.makeSnackbar(
                    getString(R.string.onFailedDataLoadingText),
                    getString(R.string.snackActionText)
                ) {
                    getMovie(movie)
                }
            }
            AppState.Loading -> binding.loading.visibility = View.VISIBLE
            is AppState.SuccessMovie -> {
                setData(appState.movieDTO)
                binding.loading.visibility = View.GONE
            }
        }
    }

    private fun setData(movieDTO: MovieDTO) {
        binding.apply {
            with(movieDTO) {
                filmDetailName.text = title
                filmDetailGenres.text = genres.toString()
                filmDetailDuration.text = runtime.toString()
                filmDetailRating.text = vote_average.toString()
                filmDetailDescription.text = overview
                filmDetailReleaseDate.text = release_date
                filmDetailImage.load(POSTER_BASE_URL + movieDTO.poster_path)
            }
        }
    }
}
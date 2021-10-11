package ru.gb.makulin.mymoviefinder.view.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentDetailsBinding
import ru.gb.makulin.mymoviefinder.facade.MovieDTO
import ru.gb.makulin.mymoviefinder.facade.MovieLoader
import ru.gb.makulin.mymoviefinder.facade.MovieLoaderListener
import ru.gb.makulin.mymoviefinder.facade.MoviesListResultDTO

class DetailsFragment : Fragment(), MovieLoaderListener {

    companion object {
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val BUNDLE_KEY = "movie_key"
    }

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
            val movie = it.getParcelable<MoviesListResultDTO>(BUNDLE_KEY)
            MovieLoader(this).loadMovie(movie!!.id)
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
                filmDetailImage.setImageResource(R.drawable.ic_launcher_background)  //FIXME later
            }
        }
    }

    override fun onLoaded(movieDTO: MovieDTO) {
        setData(movieDTO)
    }

    override fun onFailed(throwable: Throwable) {
        Log.e("mylogs", throwable.localizedMessage, throwable)
        Snackbar.make(binding.root, getString(R.string.onFailedDataLoadingText), Snackbar.LENGTH_SHORT).show()
    }

}
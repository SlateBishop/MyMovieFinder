package ru.gb.makulin.mymoviefinder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentDetailsBinding
import ru.gb.makulin.mymoviefinder.model.Movie

class DetailsFragment : Fragment() {

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
        val movie = arguments?.getParcelable(BUNDLE_KEY) ?: Movie()
        setData(movie)
    }

    private fun setData(movie: Movie) {
        binding.apply {
            filmDetailName.text = movie.name
            filmDetailGenres.text = movie.genres.toString()
            filmDetailDuration.text = movie.duration.toString()
            filmDetailRating.text = movie.ratio.toString()
            filmDetailDescription.text = movie.description
            filmDetailReleaseDate.text = movie.releaseData
            filmDetailImage.setImageResource(R.drawable.ic_launcher_background)  //FIXME later
        }
    }

}
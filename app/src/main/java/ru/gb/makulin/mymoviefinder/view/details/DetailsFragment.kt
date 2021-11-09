package ru.gb.makulin.mymoviefinder.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentDetailsBinding
import ru.gb.makulin.mymoviefinder.facade.MovieDTO
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

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            makeErrSnackbar()
//            intent?.let {
//                val movieDTO = it.getParcelableExtra<MovieDTO>(DETAILS_LOAD_RESULT_EXTRA)
//                if (movieDTO != null) {
//                    setData(movieDTO)
//                } else {
//                    makeErrSnackbar()
//                }
//            }
        }
    }

    private val movie by lazy {
        arguments?.getParcelable<MoviesListResultDTO>(BUNDLE_KEY)
    }


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
        startDetailsService()
        registerReceiver()
    }

    private fun startDetailsService() {
        if (movie != null) {
            val intent = Intent(requireContext(), DetailsService::class.java)
            intent.putExtra(ID_EXTRA, movie!!.id)
            requireContext().startService(intent)
        }
    }

    private fun registerReceiver() {
        LocalBroadcastManager
            .getInstance(requireContext())
            .registerReceiver(
                receiver,
                IntentFilter(DETAILS_INTENT_FILTER)
            )
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
        Snackbar.make(
            binding.root,
            getString(R.string.onFailedDataLoadingText),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun makeErrSnackbar() {
        val snackBar = Snackbar.make(
            binding.root,
            getString(R.string.details_snackbar_err_msg),
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction(getString(R.string.details_snackbar_err_action), View.OnClickListener {
            startDetailsService()
        })
        snackBar.show()
    }

}
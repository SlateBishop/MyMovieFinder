package ru.gb.makulin.mymoviefinder.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentMainBinding
import ru.gb.makulin.mymoviefinder.model.MoviesListResult
import ru.gb.makulin.mymoviefinder.utils.SP_IS_ADULT_SETTING
import ru.gb.makulin.mymoviefinder.utils.makeErrSnackbar
import ru.gb.makulin.mymoviefinder.utils.makeSnackbar
import ru.gb.makulin.mymoviefinder.view.details.DetailsFragment
import ru.gb.makulin.mymoviefinder.viewmodel.AppState
import ru.gb.makulin.mymoviefinder.viewmodel.MainViewModel
import java.util.function.Predicate

class MainFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val mainAdapterForTopRated = MainAdapter()
    private val mainAdapterForNew = MainAdapter()
    private val mainAdapterForUpcoming = MainAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        observeOnViewModel()
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModel.getMoviesListFromRemote()
    }

    private fun getTopRatedMovies() {
        viewModel.getTopRatedMoviesListFromServer()
    }

    private fun getNowPlayingMovies() {
        viewModel.getNowPlayingMoviesListFromServer()
    }

    private fun getUpcomingMovies() {
        viewModel.getUpcomingMoviesListFromServer()
    }

    private fun observeOnViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    private fun setAdapters() {
        with(binding) {
            topRatedRecyclerView.adapter = mainAdapterForTopRated
            mainAdapterForTopRated.setOnItemClickListener(this@MainFragment)
            newRecyclerView.adapter = mainAdapterForNew
            mainAdapterForNew.setOnItemClickListener(this@MainFragment)
            upcomingRecyclerView.adapter = mainAdapterForUpcoming
            mainAdapterForUpcoming.setOnItemClickListener(this@MainFragment)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loading.loading.visibility = View.GONE
                binding.root.makeSnackbar(
                    getString(R.string.onFailedDataLoadingText),
                    getString(R.string.snackActionText)
                ) {
                    getMoviesList()
                }
            }
            AppState.Loading -> binding.loading.loading.visibility = View.VISIBLE

            is AppState.ErrorNowPlayingMovies -> {
                binding.loading.loading.visibility = View.GONE
                binding.root.makeErrSnackbar(
                ) {
                    getNowPlayingMovies()
                }
            }
            is AppState.ErrorTopRatedMovies -> {
                binding.loading.loading.visibility = View.GONE
                binding.root.makeErrSnackbar(
                ) {
                    getTopRatedMovies()
                }
            }
            is AppState.ErrorUpcomingMovies -> {
                binding.loading.loading.visibility = View.GONE
                binding.root.makeErrSnackbar(
                ) {
                    getUpcomingMovies()
                }
            }
            is AppState.SuccessNowPlayingMovies -> {
                binding.loading.loading.visibility = View.GONE
                setDataToAdapter(mainAdapterForNew, appState.nowPlayingData.results)
            }
            is AppState.SuccessTopRatedMovies -> {
                binding.loading.loading.visibility = View.GONE
                setDataToAdapter(mainAdapterForTopRated, appState.topRatedData.results)
            }
            is AppState.SuccessUpcomingMovies -> {
                binding.loading.loading.visibility = View.GONE
                setDataToAdapter(mainAdapterForUpcoming, appState.upcomingData.results)
            }
        }
    }

    private fun setDataToAdapter(adapter: MainAdapter, data: MutableList<MoviesListResult>) {
/*
Для проверки работоспособности первого пункта ДЗ№8 необходимо раскоментировать строку ниже.
Уберутся первые фильмы каждого списка при отключенном контенте для взрослых
 */
//        data[0].adult = true
        val isAdult = isAdultSettingsOn()
        if (!isAdult) {
            filter(data, Predicate {
                it.adult
            })
        }
        adapter.setData(data)
    }

    private fun isAdultSettingsOn(): Boolean =
        sharedPreferences.getBoolean(SP_IS_ADULT_SETTING, false)


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(movie: MoviesListResult) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_KEY, movie)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("")
            .commit()
    }

    private fun <T> filter(list: MutableList<T>, predicate: Predicate<T>) {
        val itr = list.iterator()

        while (itr.hasNext()) {
            val t = itr.next()
            if (predicate.test(t)) {
                itr.remove()
            }
        }
    }
}
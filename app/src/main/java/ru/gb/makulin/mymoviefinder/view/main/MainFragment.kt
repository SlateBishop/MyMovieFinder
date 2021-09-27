package ru.gb.makulin.mymoviefinder.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentMainBinding
import ru.gb.makulin.mymoviefinder.model.Movie
import ru.gb.makulin.mymoviefinder.viewmodel.AppState
import ru.gb.makulin.mymoviefinder.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val mainAdapterForTopRated = MainAdapter()
    private val mainAdapterForNew = MainAdapter()
    private val mainAdapterForUpcoming = MainAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
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
        viewModel.getDataFromRemote()
    }

    private fun observeOnViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            renderData(it)
        })
    }

    private fun setAdapters() {
        with(binding) {
            topRatedRecyclerView.adapter = mainAdapterForTopRated
            newRecyclerView.adapter = mainAdapterForNew
            upcomingRecyclerView.adapter = mainAdapterForUpcoming
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loading.visibility = View.GONE
                val throwable = appState.error
                makeSnackbar(getString(R.string.appStateError) + throwable.localizedMessage)
            }
            AppState.Loading -> binding.loading.visibility = View.VISIBLE
            is AppState.Success -> {
                binding.loading.visibility = View.GONE
                setDataToAdapter(mainAdapterForNew, appState.newData)
                setDataToAdapter(mainAdapterForTopRated, appState.topRatedData)
                setDataToAdapter(mainAdapterForUpcoming, appState.upcomingData)
                makeSnackbar(getString(R.string.appStateSuccess))
            }
        }
    }

    private fun makeSnackbar(text: String) =
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()

    private fun setDataToAdapter(adapter: MainAdapter, data: List<Movie>) = adapter.setData(data)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
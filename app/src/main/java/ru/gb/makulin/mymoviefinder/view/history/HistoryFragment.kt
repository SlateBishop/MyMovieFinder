package ru.gb.makulin.mymoviefinder.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.gb.makulin.mymoviefinder.R
import ru.gb.makulin.mymoviefinder.databinding.FragmentHistoryBinding
import ru.gb.makulin.mymoviefinder.databinding.FragmentMainBinding
import ru.gb.makulin.mymoviefinder.model.HistoryMovieData
import ru.gb.makulin.mymoviefinder.model.MoviesListResult
import ru.gb.makulin.mymoviefinder.view.details.DetailsFragment
import ru.gb.makulin.mymoviefinder.view.main.MainAdapter
import ru.gb.makulin.mymoviefinder.view.main.MainFragment
import ru.gb.makulin.mymoviefinder.viewmodel.AppState
import ru.gb.makulin.mymoviefinder.viewmodel.HistoryViewModel
import ru.gb.makulin.mymoviefinder.viewmodel.MainViewModel

class HistoryFragment: Fragment(),OnHistoryItemClickListener {
    private var _binding: FragmentHistoryBinding? = null
    private val binding: FragmentHistoryBinding
        get() = _binding!!
    private val historyAdapter = HistoryAdapter()
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }


    companion object {
        fun newInstance() = HistoryFragment()

        const val HISTORY_BUNDLE_KEY = "history_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeOnViewModel()
    }

    private fun setAdapter() {
        binding.historyRecyclerView.adapter = historyAdapter
        historyAdapter.setOnItemClickListener(this)
    }

    private fun observeOnViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessHistory -> {
                binding.loading.loading.visibility = View.GONE
                setDataToAdapter(historyAdapter, appState.historyMovie)
            }
        }
    }

    private fun setDataToAdapter(adapter: HistoryAdapter, data: List<HistoryMovieData>) {
        adapter.setData(data)
    }

    override fun onItemClick(movie: HistoryMovieData) {
        val bundle = Bundle()
        bundle.putParcelable(HISTORY_BUNDLE_KEY, movie)
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, HistoryDetailsFragment.newInstance(bundle))
//            .addToBackStack("")
//            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
package com.sicoapp.movieapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import com.sicoapp.movieapp.utils.SEARCH_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment @Inject constructor(
    val adapter: SearchAdapter,
    var viewModel: SearchViewModel? = null
) : Fragment(R.layout.fragment_movie_search) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            viewModel ?: ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        rvMovies.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        adapter.listenerCall = object : SearchAdapter.ListenerCall {
            override fun callback(binding: ItemMovieSearchBinding) {
                binding.apply {
                    searchFragment = this@SearchFragment
                }
            }
        }

        var job: Job? = null
        etSearch.addTextChangedListener { editable: Editable? ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        subscribeToObservers(editable.toString())
                    } else {
                        adapter.clearItems()
                    }
                }
            }
        }
    }


    fun openDetails(movieId: Long, mediaTyp: String) {
        val bundlePostIdAndMediaTyp = bundleOf(ITEM_ID to movieId, MEDIATYP to mediaTyp)
        findNavController().navigate(
            R.id.action_searchFragment_to_movieDetailsFragment,
            bundlePostIdAndMediaTyp
        )
    }


    private fun subscribeToObservers(query: String) {

        val searchForImage = viewModel?.searchForImage(query)

        searchForImage?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                object : Observer<Multi> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(response: Multi) {
                        val movieResponse =
                            response
                                .results
                                .filter { !it.poster_path.isNullOrBlank() }
                                .distinctBy { it.poster_path }
                                .map { BindMulti(it) }
                        adapter.updateItems(movieResponse)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }

                    override fun onComplete() {
                    }
                }
            )
    }
}


package com.sicoapp.movieapp.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.data.database.Rating
import com.sicoapp.movieapp.data.database.UserRatingsCrossRef
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.databinding.FragmentMovieDetailsBinding
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.MEDIATYP
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    var movieId = 0L
    var mediaTyp = ""

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getLong(ITEM_ID, -1)?.let {
            movieId = it
        }
        arguments?.getString(MEDIATYP, "")?.let {
            mediaTyp = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDetailsBinding.inflate(inflater)

        if ((mediaTyp == "movie") or (mediaTyp == "")) {
            updateUIMovie(movieId)
        } else {
            getTvShowById(movieId)
        }

        binding.data = viewModel.bindDetails

        insertSmiley()

        getSmileyByMovieId()

        binding.btnDeleteAll.setOnClickListener {
            deleteSmileyByMovieId()
        }
        return binding.root
    }


    private fun insertSmiley() {
        binding.smiley.setSmileySelectedListener {
            viewModel.insertSmiley(movieId.toInt(), it.rating)
            lifecycleScope.launch {
                val currentUserID = FireStoreClass().currentUserID()
                val crossRef = UserRatingsCrossRef(currentUserID, movieId.toString())
                viewModel.insertUserMovieRatingCrossRef(crossRef)
            }
        }
    }

    private fun deleteSmileyByMovieId() {
        viewModel.deleteSmileyByMovieId(movieId.toInt())
    }


    private fun getSmileyByMovieId() {
        viewModel
            .getSmileyByMovieId(movieId.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Rating> {
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(response: Rating) {
                        val rating = response.rating
                        setFaceBackgroundColor(rating)
                    }
                    override fun onError(e: Throwable) {
                    }
                    override fun onComplete() {
                    }
                }
            )
    }


    private fun setFaceBackgroundColor(
        rating: Int?
    ) {
        lateinit var type: SmileyRating.Type
        val color = Color.YELLOW
        if (rating == 1) {
            type = SmileyRating.Type.TERRIBLE
            binding.smiley.setRating(rating, false)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 2) {
            type = SmileyRating.Type.BAD
            binding.smiley.setRating(rating, false)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 3) {
            type = SmileyRating.Type.OKAY
            binding.smiley.setRating(rating, false)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 4) {
            type = SmileyRating.Type.GOOD
            binding.smiley.setRating(rating, false)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
        if (rating == 5) {
            type = SmileyRating.Type.GREAT
            binding.smiley.setRating(rating, false)
            binding.smiley.setFaceBackgroundColor(type, color)
        }
    }

    private fun getTvShowById(movieId: Long) {
        viewModel.getTvShowById(movieId)
    }

    private fun updateUIMovie(movieId: Long) {
        viewModel.getMovieByID(movieId)
    }
}



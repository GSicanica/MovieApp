package com.sicoapp.movieapp.ui.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sicoapp.movieapp.databinding.FragmentMovieCrewBinding
import com.sicoapp.movieapp.utils.CREW_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrewMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieCrewBinding

    private val viewModel: CrewViewModel by viewModels()

    var crewId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getLong(CREW_ID, -1)?.let {
            crewId = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieCrewBinding.inflate(inflater)

        updateUI(crewId)

        binding.data = viewModel

        return binding.root
    }

    private fun updateUI(crewId :Long){
        viewModel.getCrewByMovieId(crewId)
    }
}
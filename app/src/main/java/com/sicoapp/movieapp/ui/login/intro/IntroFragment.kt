package com.sicoapp.movieapp.ui.login.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : Fragment() {

    lateinit var binding: FragmentIntroBinding

    private val viewModel by viewModels<IntroVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentIntroBinding.inflate(inflater)
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_introFragment_to_signInFragment)
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_introFragment_to_signUpFragment)
        }

        return binding.root
    }
}
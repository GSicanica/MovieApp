package com.sicoapp.movieapp.ui.login.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentEntryBinding
import com.sicoapp.movieapp.ui.BaseFragment

class SplashFragment : BaseFragment() {

    lateinit var binding: FragmentEntryBinding

    private val viewModel by viewModels<SplashVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEntryBinding.inflate(inflater)

        Handler(Looper.getMainLooper()).postDelayed({

                findNavController().navigate(
                    R.id.action_splashFragment_to_introFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(
                            R.id.splashFragment,
                            true
                        ).build()
                )
            }, 1000)

        return binding.root
    }
}
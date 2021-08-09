package com.sicoapp.movieapp.ui.login.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.data.remote.firebase.model.UserFirebase
import com.sicoapp.movieapp.databinding.FragmentSignInBinding
import com.sicoapp.movieapp.ui.BaseFragment

class SignInFragment : BaseFragment() {

    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(inflater)

        binding.btnSignIn.setOnClickListener {
            signInRegisteredUser()
        }

        return binding.root
    }

    private fun signInRegisteredUser() {
        val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
        val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            // Sign-In  FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        FireStoreClass().signInUser(this@SignInFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    fun signInSuccess(user: UserFirebase) {
        hideProgressDialog()
        findNavController().navigate(
            R.id.action_signInFragment_to_topMovieFragment)
    }
}
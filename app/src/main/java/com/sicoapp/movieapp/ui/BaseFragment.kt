package com.sicoapp.movieapp.ui

import android.app.Dialog
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sicoapp.movieapp.R
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseFragment : Fragment() {

    lateinit var dialog: Dialog

    open fun showProgressDialog(text: String) {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_progress)
        dialog.tv_progress_text.text = text
        dialog.show()
    }

    open fun showErrorSnackBar(message: String) {
        val snackBar =
            Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.snackbar_error_color
            )
        )
        snackBar.show()
    }

    open fun hideProgressDialog() {
        dialog.dismiss()
    }

    fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }
}
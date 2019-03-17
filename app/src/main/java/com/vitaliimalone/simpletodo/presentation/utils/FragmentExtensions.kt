package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showKeyboard(view: View) {
    val inputManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.showSoftInput(view, 0)
}

fun Fragment.hideKeyboard() {
    val inputManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.toast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(@StringRes text: Int) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

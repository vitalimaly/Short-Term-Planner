package com.vitaliimalone.shorttermplanner.presentation.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.hideKeyboard

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {
    private var snackbar: Snackbar? = null

    protected fun showSnackbar(
        text: String,
        actionText: String = "",
        action: (View) -> Unit = {},
        length: Int = Snackbar.LENGTH_LONG
    ) {
        snackbar = Snackbar.make(requireView(), text, length).apply {
            setAction(actionText, action)
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
        snackbar?.dismiss()
    }
}
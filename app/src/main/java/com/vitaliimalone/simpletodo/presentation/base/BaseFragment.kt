package com.vitaliimalone.simpletodo.presentation.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.vitaliimalone.simpletodo.presentation.utils.extensions.hideKeyboard

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {
    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }
}
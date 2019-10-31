package com.vitaliimalone.simpletodo.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitaliimalone.simpletodo.presentation.utils.hideKeyboard

abstract class BaseFragment(private val layoutResId: Int) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(layoutResId, container, false)
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }
}
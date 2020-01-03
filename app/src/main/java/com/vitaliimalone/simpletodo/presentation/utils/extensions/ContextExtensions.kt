package com.vitaliimalone.simpletodo.presentation.utils.extensions

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

val Context.fragmentManager: FragmentManager?
    get() = (this as? AppCompatActivity)?.supportFragmentManager
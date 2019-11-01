package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import org.koin.core.KoinComponent
import org.koin.core.inject

object Colors : KoinComponent {
    private val context: Context by inject()

    fun get(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }
}
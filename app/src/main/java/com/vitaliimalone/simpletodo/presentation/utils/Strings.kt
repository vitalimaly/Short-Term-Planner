package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import org.koin.core.KoinComponent
import org.koin.core.inject

object Strings : KoinComponent {
    private val context: Context by inject()

    fun get(@StringRes stringRes: Int): String {
        return context.getString(stringRes)
    }
}
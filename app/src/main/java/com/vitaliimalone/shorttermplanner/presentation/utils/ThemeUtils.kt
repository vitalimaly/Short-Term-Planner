package com.vitaliimalone.shorttermplanner.presentation.utils

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.Theme
import org.koin.core.KoinComponent
import org.koin.core.inject

object ThemeUtils : KoinComponent {
    private val context: Context by inject()

    fun setTheme(theme: Theme) {
        if (theme != getCurrentTheme()) {
            Pref.themeStyleResId = theme.getStyleResId()
            val mode = when (theme) {
                Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                Theme.PALE_GREEN -> AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(mode) // it recreates activity and setTheme is called in BaseActivity
        }
    }

    fun getCurrentTheme() =
        Theme.values().find { it.getStyleResId() == Pref.themeStyleResId } ?: Theme.PALE_GREEN

    @StyleRes
    fun getDefaultTheme() = if (isCurrentSystemDarkMode()) {
        R.style.AppTheme_Dark
    } else {
        R.style.AppTheme_PaleGreen
    }

    private fun isCurrentSystemDarkMode() =
        context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}
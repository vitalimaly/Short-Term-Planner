package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R

object ThemesUtils {
    fun getCurrentThemeName(): String {
        return when (Pref.theme) {
            R.style.AppTheme_PaleGreen -> Res.string(R.string.theme_name_pale_green)
            else -> Res.string(R.string.settings_theme_subtitle_default)
        }
    }

}
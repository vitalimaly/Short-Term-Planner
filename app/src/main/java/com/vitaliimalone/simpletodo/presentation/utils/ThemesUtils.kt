package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R

object ThemesUtils {
    val themes = mapOf(
            R.style.AppTheme_PaleGreen to Res.string(R.string.theme_name_pale_green)
    )

    fun getCurrentThemeName(): String {
        return themes[Pref.theme] ?: Res.string(R.string.settings_theme_subtitle_default)
    }
}
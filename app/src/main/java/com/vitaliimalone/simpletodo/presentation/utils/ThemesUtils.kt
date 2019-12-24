package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R

object ThemesUtils {
    val themes = listOf(
        Theme(R.style.AppTheme_PaleGreen, Res.string(R.string.theme_name_pale_green)),
        Theme(R.style.AppTheme_Dark, Res.string(R.string.theme_name_dark))
    )

    fun getCurrentThemeName(): String {
        return themes.find { it.resId == Pref.theme }?.name
            ?: Res.string(R.string.settings_theme_subtitle_default)
    }
}
package com.vitaliimalone.simpletodo.presentation.screens.settings.common

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.LanguageUtils
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.ThemeUtils

sealed class Setting {
    abstract fun getTitle(): String
    abstract fun getIcon(context: Context): Drawable?
    open fun getSubtitle() = ""

    object Theme : Setting() {
        override fun getTitle() = Res.string(R.string.settings_theme_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_theme)
        override fun getSubtitle() = ThemeUtils.getCurrentTheme().getTitle()
    }

    object Language : Setting() {
        override fun getTitle() = Res.string(R.string.settings_language_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_language)
        override fun getSubtitle() = LanguageUtils.getCurrentLanguage().getTitle()
    }

    object Overdue : Setting() {
        var count = 0

        override fun getTitle() = Res.string(R.string.settings_overdue_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_overdue)
        override fun getSubtitle() = Res.string(R.string.settings_overdue_subtitle, count)
    }

    object Archive : Setting() {
        var count = 0

        override fun getTitle() = Res.string(R.string.settings_archive_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_archive)
        override fun getSubtitle() = Res.string(R.string.settings_archive_subtitle, count)
    }

    object Rate : Setting() {
        override fun getTitle() = Res.string(R.string.settings_rate_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_star)
    }

    object About : Setting() {
        override fun getTitle() = Res.string(R.string.settings_about_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_about)
    }
}
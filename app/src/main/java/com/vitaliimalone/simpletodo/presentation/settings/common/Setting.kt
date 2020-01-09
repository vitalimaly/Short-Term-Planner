package com.vitaliimalone.simpletodo.presentation.settings.common

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.LanguageUtils
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.ThemeUtils

sealed class Settings {
    abstract fun getTitle(): String
    abstract fun getIcon(context: Context): Drawable
    open fun getSubtitle() = ""

    object Theme : Settings() {
        override fun getTitle() = Res.string(R.string.settings_theme_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_theme)
        override fun getSubtitle(): String = ThemeUtils.getCurrentTheme().getTitle()
    }

    object Language : Settings() {
        override fun getTitle() = Res.string(R.string.settings_language_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_language)
        override fun getSubtitle() = LanguageUtils.getCurrentLanguage().getTitle()
    }

    object Overdue : Settings() {
        var count = 0

        override fun getTitle() = Res.string(R.string.settings_overdue_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_overdue)
        override fun getSubtitle() = Res.string(R.string.settings_overdue_subtitle, count)
    }

    object Archive : Settings() {
        var count = 0

        override fun getTitle() = Res.string(R.string.settings_archive_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_archive)
        override fun getSubtitle() = Res.string(R.string.settings_archive_subtitle, count)
    }

    object Rate : Settings() {
        override fun getTitle() = Res.string(R.string.settings_rate_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_star)
    }

    object Info : Settings() {
        override fun getTitle() = Res.string(R.string.settings_info_title)
        override fun getIcon(context: Context) = Res.drawable(context, R.drawable.ic_info)
    }
}
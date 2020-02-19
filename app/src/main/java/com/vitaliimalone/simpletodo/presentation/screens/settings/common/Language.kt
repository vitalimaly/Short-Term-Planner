package com.vitaliimalone.simpletodo.presentation.screens.settings.common

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
import java.util.Locale

enum class Language {
    ENGLISH {
        override fun getLocale(): Locale = Locale.ENGLISH
        override fun getTitle() = Locale.ENGLISH.displayLanguage.capitalize()
        override fun getIcon(context: Context): Drawable? = Res.drawable(context, R.drawable.ic_country_flag_gb)
    },

    RUSSIAN {
        override fun getLocale(): Locale = Locale("ru")
        override fun getTitle() = Locale("ru").displayLanguage.capitalize()
        override fun getIcon(context: Context): Drawable? = Res.drawable(context, R.drawable.ic_country_flag_ru)
    };

    abstract fun getLocale(): Locale
    abstract fun getTitle(): String
    abstract fun getIcon(context: Context): Drawable?
}
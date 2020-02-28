package com.vitaliimalone.shorttermplanner.presentation.screens.settings.common

import android.content.Context
import android.graphics.drawable.Drawable
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
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
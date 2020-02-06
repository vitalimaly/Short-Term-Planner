package com.vitaliimalone.simpletodo.presentation.settings.common

import android.graphics.drawable.Drawable
import java.util.Locale

enum class Language {
    ENGLISH {
        override fun getLocaleCode(): String = Locale.ENGLISH.language
        override fun getTitle() = Locale.ENGLISH.displayLanguage.capitalize()
        override fun getIcon(): Drawable? = null
    },

    RUSSIAN {
        override fun getLocaleCode(): String = Locale("ru").language
        override fun getTitle() = Locale("ru").displayLanguage.capitalize()
        override fun getIcon(): Drawable? = null
    };

    abstract fun getLocaleCode(): String
    abstract fun getTitle(): String
    abstract fun getIcon(): Drawable?
}
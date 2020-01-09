package com.vitaliimalone.simpletodo.presentation.settings.common

import android.graphics.drawable.Drawable
import java.util.Locale

enum class Language {
    ENGLISH {
        override val localeCode: String
            get() = Locale.ENGLISH.language
        override val title: String
            get() = Locale.ENGLISH.displayLanguage.capitalize()
        override val icon: Drawable?
            get() = null
    },

    RUSSIAN {
        override val localeCode: String
            get() = Locale("ru").language
        override val title: String
            get() = Locale("ru").displayLanguage.capitalize()
        override val icon: Drawable?
            get() = null
    };

    abstract val localeCode: String
    abstract val title: String
    abstract val icon: Drawable?
}
package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.content.res.Configuration
import com.vitaliimalone.simpletodo.presentation.models.Language
import java.util.Locale

// https://medium.com/ironsource-tech-blog/conversion-by-translation-changing-your-android-app-language-at-runtime-5c9daebf9771
object LanguageUtils {
    fun wrapContext(context: Context): Context {
        val savedLocale = Locale(getLocaleCode(context))
        Locale.setDefault(savedLocale)
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)
        return context.createConfigurationContext(newConfig)
    }

    @Suppress("DEPRECATION")
    fun overrideLocale(context: Context) {
        val savedLocale = Locale(getLocaleCode(context))
        Locale.setDefault(savedLocale)
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)
        context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)
        if (context != context.applicationContext) {
            context.applicationContext.resources.run { updateConfiguration(newConfig, displayMetrics) }
        }
    }

    fun setLanguage(context: Context, language: Language) {
        Pref.localeCode = language.localeCode
        overrideLocale(context)
    }

    fun getCurrentLanguage(): Language {
        return Language.values().find { it.localeCode == Pref.localeCode } ?: Language.ENGLISH
    }

    private fun getLocaleCode(context: Context): String { // just for locale in order to keep Prefs.kt clean
        return context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getString(Pref.LOCALE_CODE_KEY, Language.ENGLISH.localeCode) ?: Language.ENGLISH.localeCode
    }
}
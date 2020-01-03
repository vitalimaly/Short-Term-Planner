package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.content.res.Configuration
import com.vitaliimalone.simpletodo.presentation.models.Language
import java.util.Locale

// https://medium.com/ironsource-tech-blog/conversion-by-translation-changing-your-android-app-language-at-runtime-5c9daebf9771
object LanguageUtils {
    fun wrapContext(context: Context): Context {
        val savedLocale = Locale(Pref(context).localeCode)
        Locale.setDefault(savedLocale)
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)
        return context.createConfigurationContext(newConfig)
    }

    @Suppress("DEPRECATION")
    fun overrideLocale(context: Context) {
        val savedLocale = Locale(Pref(context).localeCode)
        Locale.setDefault(savedLocale)
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)
        context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)
        if (context != context.applicationContext) {
            context.applicationContext.resources.run { updateConfiguration(newConfig, displayMetrics) }
        }
    }

    fun setLanguage(context: Context, language: Language) {
        Pref(context).localeCode = language.localeCode
        overrideLocale(context)
    }

    fun getCurrentLanguage(context: Context): Language {
        return Language.values().find { it.localeCode == Pref(context).localeCode } ?: Language.ENGLISH
    }
}
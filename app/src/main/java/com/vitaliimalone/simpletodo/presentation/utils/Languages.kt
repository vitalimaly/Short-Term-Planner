package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import java.util.Locale

data class Language(val localeLanguage: String, val name: String, val icon: Drawable? = null)

// https://medium.com/ironsource-tech-blog/conversion-by-translation-changing-your-android-app-language-at-runtime-5c9daebf9771
object Languages {
    val languages = listOf(
        Language(Constants.ENGLISH_LOCALE_LANGUAGE, Constants.ENGLISH_LANGUAGE_NAME),
        Language(Constants.RUSSIAN_LOCALE_LANGUAGE, Constants.RUSSIAN_LANGUAGE_NAME)
    )

    fun wrapContext(context: Context): Context {
        val savedLocale = Locale(Pref(context).localeLanguage)
        Locale.setDefault(savedLocale)
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)
        return context.createConfigurationContext(newConfig)
    }

    fun overrideLocale(context: Context) {
        val savedLocale = Locale(Pref(context).localeLanguage)
        Locale.setDefault(savedLocale)
        val newConfig = Configuration()
        newConfig.setLocale(savedLocale)
        context.resources.updateConfiguration(newConfig, context.resources.displayMetrics)
        if (context != context.applicationContext) {
            context.applicationContext.resources.run { updateConfiguration(newConfig, displayMetrics) }
        }
    }

    fun getCurrentLanguage(context: Context): Language {
        return languages.find { it.localeLanguage == Pref(context).localeLanguage } ?: languages[0]
    }

    fun setLanguage(context: Context, language: Language) {
        Pref(context).localeLanguage = language.localeLanguage
        overrideLocale(context)
    }
}

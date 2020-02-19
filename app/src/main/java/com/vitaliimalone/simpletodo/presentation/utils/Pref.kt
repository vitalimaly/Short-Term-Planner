package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.screens.settings.common.Language

object Pref : Preferences(Constants.PREFERENCES_NAME) {
    const val LOCALE_LANGUAGE_KEY = "localeLanguage"

    var localeLanguage by stringPref(
        defaultValue = Language.ENGLISH.getLocale().language,
        prefKey = LOCALE_LANGUAGE_KEY
    )
    var themeStyleResId by intPref(defaultValue = R.style.AppTheme_PaleGreen)
    var rateAppLaunchCount by intPref()
    var rateAppLaunchDateTime by offsetDateTimePref()
    var rateAppNeverShowClicked by booleanPref()
}
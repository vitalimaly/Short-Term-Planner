package com.vitaliimalone.shorttermplanner.presentation.utils

import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.Language

object Pref : Preferences(Constants.PREFERENCES_NAME) {
    const val LOCALE_LANGUAGE_KEY = "localeLanguage"

    var localeLanguage by stringPref(
        defaultValue = Language.ENGLISH.getLocale().language,
        prefKey = LOCALE_LANGUAGE_KEY
    )
    var themeStyleResId by intPref(defaultValue = ThemeUtils.getDefaultTheme())
    var rateAppLaunchCount by intPref()
    var rateAppLaunchDateTime by offsetDateTimePref()
    var rateAppNeverShowClicked by booleanPref()
}
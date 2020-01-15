package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.settings.common.Language

object Pref : Preferences(Constants.PREFERENCES_NAME) {
    const val LOCALE_CODE_KEY = "localeCode"

    var localeCode by stringPref(defaultValue = Language.ENGLISH.getLocaleCode(), prefKey = LOCALE_CODE_KEY)
    var themeStyleResId by intPref(defaultValue = R.style.AppTheme_PaleGreen)
    var rateAppLaunchCount by intPref()
    var rateAppLaunchDateTime by offsetDateTimePref()
    var rateAppDontShow by booleanPref()
}
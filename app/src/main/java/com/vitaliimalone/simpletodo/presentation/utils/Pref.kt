package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import com.vitaliimalone.simpletodo.R

class Pref(context: Context) : Preferences(context) {
    var theme by intPref(defaultValue = R.style.AppTheme_PaleGreen)
    var localeLanguage by stringPref(defaultValue = Constants.RUSSIAN_LOCALE_LANGUAGE)
}
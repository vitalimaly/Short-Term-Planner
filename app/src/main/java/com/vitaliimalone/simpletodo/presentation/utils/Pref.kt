package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.models.Language

class Pref(context: Context) : Preferences(context) {
    var themeStyleResId by intPref(defaultValue = R.style.AppTheme_PaleGreen)
    var localeCode by stringPref(defaultValue = Language.ENGLISH.localeCode)
}
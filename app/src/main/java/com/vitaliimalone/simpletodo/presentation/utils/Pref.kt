package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R

object Pref : Preferences() {
    var theme by intPref(defaultValue = R.style.AppTheme_PaleGreen)
}
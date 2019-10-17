package com.vitaliimalone.simpletodo.presentation.models

import androidx.annotation.StringRes
import com.vitaliimalone.simpletodo.R

enum class HomeTab(@StringRes val title: Int) {
    TODAY(R.string.home_tab_today),
    WEEK(R.string.home_tab_week),
    MONTH(R.string.home_tab_month),
    TODO(R.string.home_tab_todo)
}
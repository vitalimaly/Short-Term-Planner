package com.vitaliimalone.simpletodo.presentation.models

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Strings

enum class HomeTab(val title: String) {
    TODAY(Strings.get(R.string.home_tab_today)),
    WEEK(Strings.get(R.string.home_tab_week)),
    MONTH(Strings.get(R.string.home_tab_month)),
    TODO(Strings.get(R.string.home_tab_todo))
}
package com.vitaliimalone.simpletodo.presentation.models

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res

enum class HomeTab(val title: String) {
    TODAY(Res.string(R.string.home_tab_today)),
    WEEK(Res.string(R.string.home_tab_week)),
    MONTH(Res.string(R.string.home_tab_month)),
    TODO(Res.string(R.string.home_tab_todo))
}
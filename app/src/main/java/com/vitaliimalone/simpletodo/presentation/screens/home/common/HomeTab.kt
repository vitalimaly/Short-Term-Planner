package com.vitaliimalone.simpletodo.presentation.screens.home.common

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res

enum class HomeTab {
    TODAY {
        override fun getTitle() = Res.string(R.string.home_tab_today)
    },

    WEEK {
        override fun getTitle() = Res.string(R.string.home_tab_week)
    },

    MONTH {
        override fun getTitle() = Res.string(R.string.home_tab_month)
    },

    TODO {
        override fun getTitle() = Res.string(R.string.home_tab_todo)
    };

    abstract fun getTitle(): String
}
package com.vitaliimalone.simpletodo.presentation.models

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res

enum class HomeTab {
    TODAY {
        override val title: String
            get() = Res.string(R.string.home_tab_today)
    },

    WEEK {
        override val title: String
            get() = Res.string(R.string.home_tab_week)
    },

    MONTH {
        override val title: String
            get() = Res.string(R.string.home_tab_month)
    },

    TODO {
        override val title: String
            get() = Res.string(R.string.home_tab_todo)
    };

    abstract val title: String
}
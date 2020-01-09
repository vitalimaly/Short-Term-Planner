package com.vitaliimalone.simpletodo.presentation.settings.common

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res

enum class Theme {
    PALE_GREEN {
        override val styleResId: Int
            get() = R.style.AppTheme_PaleGreen
        override val title: String
            get() = Res.string(R.string.theme_name_pale_green)
    },
    DARK {
        override val styleResId: Int
            get() = R.style.AppTheme_Dark
        override val title: String
            get() = Res.string(R.string.theme_name_dark)
    };

    abstract val styleResId: Int
    abstract val title: String
}
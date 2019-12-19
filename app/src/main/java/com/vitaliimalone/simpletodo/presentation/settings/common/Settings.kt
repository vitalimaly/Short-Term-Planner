package com.vitaliimalone.simpletodo.presentation.settings.common

import android.graphics.drawable.Drawable
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.ThemesUtils

enum class Settings(val icon: Drawable, val title: String, var subtitile: String = "") {
    THEME(Res.drawable(R.drawable.ic_theme), Res.string(R.string.settings_theme_title), ThemesUtils.getCurrentThemeName()),
    OVERDUE(Res.drawable(R.drawable.ic_overdue), Res.string(R.string.settings_overdue_title), Res.string(R.string.settings_overdue_subtitle, 0)),
    ARCHIVE(Res.drawable(R.drawable.ic_archive), Res.string(R.string.settings_archive_title), Res.string(R.string.settings_archive_subtitle, 0)),
    RATE(Res.drawable(R.drawable.ic_star), Res.string(R.string.settings_rate_title)),
    INFO(Res.drawable(R.drawable.ic_info), Res.string(R.string.settings_info_title))
}


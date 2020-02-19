package com.vitaliimalone.simpletodo.presentation.utils

import android.os.Build
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.settings.common.Theme

object ThemeUtils {
    fun setTheme(activity: FragmentActivity, theme: Theme = getCurrentTheme()) {
        activity.setTheme(theme.getStyleResId())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Res.boolean(activity, R.attr.themeIsLight)) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView.systemUiVisibility = 0
            }
        }
        Pref.themeStyleResId = theme.getStyleResId()
    }

    fun getCurrentTheme() =
        Theme.values().find { it.getStyleResId() == Pref.themeStyleResId } ?: Theme.PALE_GREEN
}
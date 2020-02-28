package com.vitaliimalone.shorttermplanner.presentation.utils

import android.os.Build
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.Theme

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
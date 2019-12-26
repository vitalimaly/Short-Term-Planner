package com.vitaliimalone.simpletodo.presentation.utils

import android.os.Build
import android.view.View
import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentActivity
import com.vitaliimalone.simpletodo.R

data class Theme(@StyleRes val resId: Int, val name: String)

object Themes {
    val themes = listOf(
        Theme(R.style.AppTheme_PaleGreen, Res.string(R.string.theme_name_pale_green)),
        Theme(R.style.AppTheme_Dark, Res.string(R.string.theme_name_dark))
    )

    fun setTheme(activity: FragmentActivity, @StyleRes themeResId: Int = Pref.theme) {
        activity.setTheme(themeResId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Res.boolean(activity, R.attr.themeIsLight)) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView.systemUiVisibility = 0
            }
        }
        Pref.theme = themeResId
    }

    fun getCurrentTheme(): Theme {
        return themes.find { it.resId == Pref.theme } ?: themes[0]
    }
}
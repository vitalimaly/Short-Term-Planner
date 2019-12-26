package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.koin.core.KoinComponent
import org.koin.core.inject

object Res : KoinComponent {
    private val context: Context by inject()

    fun string(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return context.getString(stringRes, *formatArgs)
    }

    @ColorInt
    fun color(context: Context, @AttrRes attrResId: Int, @StyleRes themeResId: Int = Pref.theme): Int {
        val theme = context.resources.newTheme()
        theme.applyStyle(themeResId, false)
        val a = theme.obtainStyledAttributes(intArrayOf(attrResId))
        return try {
            a.getColor(0, Color.RED) // red to show errors
        } finally {
            a.recycle()
        }
    }

    fun drawable(@DrawableRes drawableRes: Int): Drawable {
        return ContextCompat.getDrawable(context, drawableRes)!!
    }

    fun dimen(@DimenRes dimenRes: Int): Float {
        return context.resources.getDimension(dimenRes)
    }

    fun font(@FontRes fontRes: Int): Typeface {
        return ResourcesCompat.getFont(context, fontRes)!!
    }
}
package com.vitaliimalone.shorttermplanner.presentation.utils

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.koin.core.KoinComponent
import org.koin.core.inject

object Res : KoinComponent {
    private val context: Context by inject()

    fun string(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()) =
        context.getString(stringRes, *formatArgs)

    @ColorInt
    fun color(context: Context, @AttrRes attrResId: Int): Int {
        val a = context.obtainStyledAttributes(null, intArrayOf(attrResId))
        return try {
            a.getColor(0, Color.DKGRAY)
        } finally {
            a.recycle()
        }
    }

    @ColorInt
    fun themeColor(context: Context, @AttrRes attrResId: Int, @StyleRes themeResId: Int = Pref.themeStyleResId): Int {
        val theme = context.resources.newTheme()
        theme.applyStyle(themeResId, false)
        val a = theme.obtainStyledAttributes(intArrayOf(attrResId))
        return try {
            a.getColor(0, Color.DKGRAY)
        } finally {
            a.recycle()
        }
    }

    fun drawable(context: Context, @DrawableRes drawableRes: Int) = ContextCompat.getDrawable(context, drawableRes)

    @Px
    fun dimen(context: Context, @DimenRes dimenRes: Int) = context.resources.getDimension(dimenRes)

    fun font(context: Context, @FontRes fontRes: Int) = ResourcesCompat.getFont(context, fontRes)

    fun boolean(context: Context, @AttrRes attrResId: Int): Boolean {
        val a = context.obtainStyledAttributes(null, intArrayOf(attrResId))
        return try {
            a.getBoolean(0, false)
        } finally {
            a.recycle()
        }
    }
}
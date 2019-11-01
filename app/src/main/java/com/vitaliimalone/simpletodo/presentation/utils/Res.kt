package com.vitaliimalone.simpletodo.presentation.utils

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.koin.core.KoinComponent
import org.koin.core.inject

object Res : KoinComponent {
    private val context: Context by inject()

    fun string(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return context.getString(stringRes, *formatArgs)
    }

    fun color(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
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
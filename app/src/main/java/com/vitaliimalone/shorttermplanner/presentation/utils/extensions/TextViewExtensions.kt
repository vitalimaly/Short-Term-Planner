package com.vitaliimalone.shorttermplanner.presentation.utils.extensions

import android.graphics.Paint
import android.widget.TextView

fun TextView.showStrikeThrough(show: Boolean) {
    alpha = if (show) 0.4f else 1.0f
    paintFlags = if (show) {
        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

package com.vitaliimalone.simpletodo.presentation.utils.extensions

import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

fun String.setTextColor(@ColorInt color: Int): SpannableString {
    return SpannableString(this).apply {
        setSpan(ForegroundColorSpan(color), 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

val CharSequence?.trimmed: String
    get() {
        return this?.trim()?.toString() ?: ""
    }

val Editable?.trimmed: String
    get() {
        return this?.trim()?.toString() ?: ""
    }
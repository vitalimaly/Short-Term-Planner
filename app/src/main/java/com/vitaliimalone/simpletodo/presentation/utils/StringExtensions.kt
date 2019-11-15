package com.vitaliimalone.simpletodo.presentation.utils

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

val CharSequence?.trimmedText: String
    get() {
        return this?.trim()?.toString() ?: ""
    }

val Editable?.trimmedText: String
    get() {
        return this?.trim()?.toString() ?: ""
    }
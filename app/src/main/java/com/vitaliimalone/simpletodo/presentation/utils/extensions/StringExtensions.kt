package com.vitaliimalone.simpletodo.presentation.utils.extensions

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

fun String.setTextColor(@ColorInt color: Int): SpannableString {
    return SpannableString(this).apply {
        setSpan(ForegroundColorSpan(color), 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

val CharSequence?.trimmed: String
    get() {
        return this?.trim()?.toString() ?: ""
    }

fun String.toOffsetDateTime(): OffsetDateTime {
    return DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(this, OffsetDateTime::from)
}

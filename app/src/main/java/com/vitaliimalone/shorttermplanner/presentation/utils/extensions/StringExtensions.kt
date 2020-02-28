package com.vitaliimalone.shorttermplanner.presentation.utils.extensions

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

val CharSequence?.trimmed: String
    get() = this?.trim()?.toString() ?: ""

fun String.toOffsetDateTime(): OffsetDateTime = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(this, OffsetDateTime::from)

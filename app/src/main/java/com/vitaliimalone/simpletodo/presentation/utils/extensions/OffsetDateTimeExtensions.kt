package com.vitaliimalone.simpletodo.presentation.utils.extensions

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

fun OffsetDateTime.toIsoDateTimeString(): String = this.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
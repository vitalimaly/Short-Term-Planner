package com.vitaliimalone.simpletodo.presentation.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {
    const val DAY_OF_WEEK_AND_MONTH_AND_DAY_FULL = "EEEE, MMMM d"

    fun getTodayDayOfWeekAndMonthAndDayFull(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DAY_OF_WEEK_AND_MONTH_AND_DAY_FULL))
    }
}
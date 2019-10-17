package com.vitaliimalone.simpletodo.presentation.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {
    const val TODAY_HOME_DATE_FORMAT = "EEEE, MMMM d"

    fun getTodayHomeDate(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getWeekRangeHomeDate(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getMonthRangeHomeDate(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getTodoRangeHomeDate(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }
}
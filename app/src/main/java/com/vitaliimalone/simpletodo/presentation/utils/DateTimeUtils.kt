package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {
    private const val TODAY_HOME_DATE_FORMAT = "EEEE, MMMM d"
    const val TASK_DATE_FORMAT = "EEE, MMM d"

    fun getTodayHomeDate(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getWeekRangeHomeDate(): String { // todo
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getMonthRangeHomeDate(): String { // todo
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getTodoRangeHomeDate(): String { // todo
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TODAY_HOME_DATE_FORMAT))
    }

    fun getTaskDueDate(dueDate: OffsetDateTime): String {
        val today = LocalDate.now()
        val localDueDate = dueDate.toLocalDate()
        return when {
            today == localDueDate -> Strings.get(R.string.today_date)
            today.plusDays(1) == localDueDate -> Strings.get(R.string.tomorrow_date)
            else -> dueDate.format(DateTimeFormatter.ofPattern(TASK_DATE_FORMAT))
        }
    }
}
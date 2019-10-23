package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateTimeUtils {
    private const val FULL_DAY_MONTH_FORMAT = "EEEE, MMMM d"
    private const val SHORT_DAY_MONTH_FORMAT = "EEE, MMM d"

    fun getDateForTab(homeTab: HomeTab): String {
        return when (homeTab) {
            HomeTab.TODAY -> {
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(FULL_DAY_MONTH_FORMAT))
            }
            HomeTab.WEEK -> {
                val start = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT)) // todo fix same as GetTasksForHomeTabUseCase
                val end = LocalDateTime.now().plusDays(6).format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                "$start - $end"
            }
            HomeTab.MONTH -> {
                val start = LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                val end = LocalDateTime.now().plusDays(23).format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                "$start - $end"
            }
            HomeTab.TODO -> {
                val start = LocalDateTime.now().plusDays(24).format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                "${Strings.get(R.string.from_date)} $start"
            }
        }
    }

    fun getTaskDueDate(dueDate: OffsetDateTime): String {
        val today = LocalDate.now()
        val localDueDate = dueDate.toLocalDate()
        return when {
            today == localDueDate -> Strings.get(R.string.today_date)
            today.plusDays(1) == localDueDate -> Strings.get(R.string.tomorrow_date)
            else -> dueDate.format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
        }
    }
}
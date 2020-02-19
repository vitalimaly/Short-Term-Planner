package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.home.common.HomeTab
import org.threeten.bp.DayOfWeek
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAdjusters

object DateTimeUtils {
    private const val FULL_DAY_MONTH_FORMAT = "EEEE, MMMM d"
    private const val SHORT_DAY_MONTH_FORMAT = "EEE, MMM d"

    fun getTabStartEndDateText(homeTab: HomeTab): String {
        val (startDate, endDate) = getTabStartEndDate(homeTab)
        return when (homeTab) {
            HomeTab.TODAY -> {
                startDate.format(DateTimeFormatter.ofPattern(FULL_DAY_MONTH_FORMAT))
            }
            HomeTab.WEEK, HomeTab.MONTH -> {
                val start = startDate.format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                val end = endDate.format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                "$start - $end"
            }
            HomeTab.TODO -> {
                val start = startDate.format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
                "${Res.string(R.string.from_date)} $start"
            }
        }
    }

    fun getAddNewTaskDate(homeTab: HomeTab): OffsetDateTime {
        val (startDate, endDate) = getTabStartEndDate(homeTab)
        val currentTime = LocalTime.now()
        return when (homeTab) {
            HomeTab.TODAY, HomeTab.WEEK, HomeTab.MONTH -> {
                endDate.with(currentTime)
            }
            HomeTab.TODO -> {
                startDate.with(TemporalAdjusters.lastDayOfYear()).with(currentTime)
            }
        }
    }

    fun getTaskDueDateText(dueDate: OffsetDateTime): String {
        val today = LocalDate.now()
        val localDueDate = dueDate.toLocalDate()
        return when {
            today == localDueDate -> Res.string(R.string.today_date)
            today.plusDays(1) == localDueDate -> Res.string(R.string.tomorrow_date)
            else -> dueDate.format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))
        }
    }

    fun getTaskDueDateFullText(dueDate: OffsetDateTime): String {
        val today = LocalDate.now()
        val localDueDate = dueDate.toLocalDate()
        return when {
            today == localDueDate -> Res.string(R.string.today_date)
            today.plusDays(1) == localDueDate -> Res.string(R.string.tomorrow_date)
            else -> dueDate.format(DateTimeFormatter.ofPattern(FULL_DAY_MONTH_FORMAT))
        }
    }

    fun getTabStartEndDate(homeTab: HomeTab): Pair<OffsetDateTime, OffsetDateTime> {
        val now = OffsetDateTime.now()
        val startDate: OffsetDateTime
        val endDate: OffsetDateTime
        val nextMondayAdjuster = TemporalAdjusters.next(DayOfWeek.MONDAY)
        when (homeTab) {
            HomeTab.TODAY -> {
                startDate = now.with(LocalTime.MIN)
                endDate = now.with(LocalTime.MAX)
            }
            HomeTab.WEEK -> {
                if (now.dayOfWeek < DayOfWeek.SUNDAY) {
                    startDate = now.plusDays(1)
                        .with(LocalTime.MIN)
                    endDate = now.with(DayOfWeek.SUNDAY)
                        .with(LocalTime.MAX)
                } else {
                    startDate = now.plusWeeks(1)
                        .with(DayOfWeek.MONDAY)
                        .with(LocalTime.MIN)
                    endDate = now.plusWeeks(1)
                        .with(DayOfWeek.SUNDAY)
                        .with(LocalTime.MAX)
                }
            }
            HomeTab.MONTH -> {
                if (now.dayOfWeek < DayOfWeek.SATURDAY) {
                    startDate = now.with(nextMondayAdjuster)
                        .with(LocalTime.MIN)
                    endDate = now.with(nextMondayAdjuster)
                        .with(TemporalAdjusters.lastDayOfMonth())
                        .with(LocalTime.MAX)
                } else {
                    startDate = now.plusWeeks(1)
                        .with(nextMondayAdjuster)
                        .with(LocalTime.MIN)
                    endDate = now.plusWeeks(1)
                        .with(nextMondayAdjuster)
                        .with(TemporalAdjusters.lastDayOfMonth())
                        .with(LocalTime.MAX)
                }
            }
            HomeTab.TODO -> {
                startDate = now.plusWeeks(1)
                    .with(nextMondayAdjuster)
                    .with(TemporalAdjusters.lastDayOfMonth())
                    .plusDays(1)
                    .with(LocalTime.MIN)
                endDate = now.withYear(Constants.MAX_YEAR)
                    .with(LocalTime.MAX)
            }
        }
        return startDate to endDate
    }

    fun getShortDayMonthDateText(offsetDateTime: OffsetDateTime): String =
        offsetDateTime.format(DateTimeFormatter.ofPattern(SHORT_DAY_MONTH_FORMAT))

    fun getDueDatePopupEndOfWeekDateText() = if (OffsetDateTime.now().dayOfWeek < DayOfWeek.SATURDAY) {
        Res.string(R.string.due_date_popup_this_saturday)
    } else {
        Res.string(R.string.due_date_popup_next_saturday)
    }

    fun getMinDate(): OffsetDateTime {
        val minInstant = Instant.ofEpochMilli(Long.MIN_VALUE)
        return OffsetDateTime.ofInstant(minInstant, ZoneOffset.UTC)
    }

    fun getPreviousDayEndDate(): OffsetDateTime = OffsetDateTime.now().minusDays(1).with(LocalTime.MAX)
}
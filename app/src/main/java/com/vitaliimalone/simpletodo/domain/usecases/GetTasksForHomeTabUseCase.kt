package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAdjusters

class GetTasksForHomeTabUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun getTasksForTab(tab: HomeTab): Flow<List<Task>> {
        val startDate: OffsetDateTime
        val endDate: OffsetDateTime
        val now = OffsetDateTime.now()
        val nextMondayAdjuster = TemporalAdjusters.next(DayOfWeek.MONDAY)
        when (tab) {
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
        val startDateString = startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val endDateString = endDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return taskRepository.getUnarchivedTasksForPeriod(startDateString, endDateString).map { it.map(TaskMapper::map) }
    }
}
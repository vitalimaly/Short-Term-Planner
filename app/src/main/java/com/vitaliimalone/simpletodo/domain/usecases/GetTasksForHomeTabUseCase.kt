package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAdjusters

class GetTasksForHomeTabUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun getTasksForTab(tab: HomeTab): Flow<List<Task>> {
        val startDate: String
        val endDate: String
        when (tab) {
            HomeTab.TODAY -> {
                startDate = OffsetDateTime.now()
                        .with(LocalTime.MIN)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                endDate = OffsetDateTime.now()
                        .with(LocalTime.MAX)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            }
            HomeTab.WEEK -> {
                val todayDayOfWeek = LocalDate.now().dayOfWeek
                if (todayDayOfWeek < DayOfWeek.SUNDAY) {
                    startDate = OffsetDateTime.now()
                            .plusDays(1)
                            .with(LocalTime.MIN)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    endDate = OffsetDateTime.now()
                            .with(DayOfWeek.SUNDAY)
                            .with(LocalTime.MAX)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                } else {
                    // next week
                    startDate = OffsetDateTime.now()
                            .plusWeeks(1)
                            .with(DayOfWeek.MONDAY)
                            .with(LocalTime.MIN)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    endDate = OffsetDateTime.now()
                            .plusWeeks(1)
                            .with(DayOfWeek.SUNDAY)
                            .with(LocalTime.MIN)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                }
            }
            HomeTab.MONTH -> {
                if (true) { // todo fix
                    startDate = OffsetDateTime.now()
                            .plusDays(7) // todo fix
                            .with(LocalTime.MIN)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    endDate = OffsetDateTime.now()
                            .plusDays(23) // todo fix
                            .with(LocalTime.MAX)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                } else {
                    // next month
                    startDate = OffsetDateTime.now()
                            .plusMonths(1)
                            .with(TemporalAdjusters.firstDayOfMonth())
                            .with(LocalTime.MIN)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    endDate = OffsetDateTime.now()
                            .plusMonths(1)
                            .with(TemporalAdjusters.lastDayOfMonth())
                            .with(LocalTime.MIN)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                }
            }
            HomeTab.TODO -> { // todo fix
                startDate = OffsetDateTime.now()
                        .plusDays(23) // todo fix
                        .with(LocalTime.MIN)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                endDate = OffsetDateTime.now()
                        .withYear(Constants.MAX_YEAR)
                        .with(OffsetTime.MAX)
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }
        }
        return taskRepository.getUnarchivedTasksForPeriod(startDate, endDate).map { it.map(TaskMapper::map) }
    }
}
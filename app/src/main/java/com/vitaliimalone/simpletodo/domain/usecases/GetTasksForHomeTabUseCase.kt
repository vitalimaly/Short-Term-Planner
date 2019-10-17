package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.OffsetTime
import org.threeten.bp.format.DateTimeFormatter

class GetTasksForHomeTabUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun getTasksForTab(tab: HomeTab): Flow<List<Task>> { // todo fix
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
                startDate = OffsetDateTime.now()
                        .plusDays(1)
                        .with(LocalTime.MIN)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                endDate = OffsetDateTime.now()
                        .plusDays(6) // todo fix
                        .with(LocalTime.MAX)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            }
            HomeTab.MONTH -> {
                startDate = OffsetDateTime.now()
                        .plusDays(7) // todo fix
                        .with(LocalTime.MIN)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                endDate = OffsetDateTime.now()
                        .plusDays(23) // todo fix
                        .with(LocalTime.MAX)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            }
            HomeTab.TODO -> {
                startDate = OffsetDateTime.now()
                        .plusDays(23) // todo fix
                        .with(LocalTime.MIN)
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                endDate = OffsetDateTime.now()
                        .withYear(9999) // todo ?
                        .with(OffsetTime.MAX)
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }
        }
        return taskRepository.getUnarchivedTasksForPeriod(startDate, endDate).map { it.map(TaskMapper::map) }
    }
}
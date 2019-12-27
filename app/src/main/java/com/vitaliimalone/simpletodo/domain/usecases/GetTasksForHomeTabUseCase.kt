package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.HomeTabType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.format.DateTimeFormatter

class GetTasksForHomeTabUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun getTasksForTab(homeTab: HomeTabType): Flow<List<Task>> {
        val (startDate, endDate) = DateTimeUtils.getStartEndDateForTab(homeTab)
        val startDateString = startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val endDateString = endDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return taskRepository.getUnarchivedTasksForPeriod(startDateString, endDateString)
            .map { it.map(TaskMapper::map) }
            .map { it.sortedBy(Task::dueTo) }
    }
}
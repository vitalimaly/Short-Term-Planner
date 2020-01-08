package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class GetOverdueTasksCountUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun getOverdueTasksCount(): Flow<Int> {
        val (startDate, endDate) = DateTimeUtils.getMinDate() to OffsetDateTime.now()
        val startDateString = startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val endDateString = endDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return taskRepository.getTasksCountForPeriod(startDateString, endDateString)
    }
}
package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import org.threeten.bp.format.DateTimeFormatter

class DeleteOverdueTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun deleteUnarchivedOverdueTasks() {
        val (startDate, endDate) = DateTimeUtils.getMinDate() to DateTimeUtils.getPreviousDayEndDate()
        val startDateString = startDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val endDateString = endDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return taskRepository.deleteUnarchivedTasksForPeriod(startDateString, endDateString)
    }
}
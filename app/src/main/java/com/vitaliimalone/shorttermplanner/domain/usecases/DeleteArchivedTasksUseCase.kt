package com.vitaliimalone.shorttermplanner.domain.usecases

import com.vitaliimalone.shorttermplanner.data.repository.TaskRepository

class DeleteArchivedTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun deleteArchivedTasks() = taskRepository.deleteArchivedTasks()
}
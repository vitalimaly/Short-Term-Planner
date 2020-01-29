package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository

class DeleteArchivedTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun deleteArchivedTasks() {
        taskRepository.deleteArchivedTasks()
    }
}
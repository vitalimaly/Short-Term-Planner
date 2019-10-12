package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task

class GetTasksUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun getAllTasks(): List<Task> {
        return taskRepository.getAllTasks().map(TaskMapper::map)
    }

    suspend fun getAllTasks(isArchived: Boolean): List<Task> {
        return taskRepository.getAllTasks(isArchived).map(TaskMapper::map)
    }
}
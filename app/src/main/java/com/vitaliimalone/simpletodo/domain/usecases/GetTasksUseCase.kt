package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun getTasks(): Flow<List<Task>> {
        return taskRepository.getAllTasks().map { it.map(TaskMapper::map) }
    }

    suspend fun getTasks(isArchived: Boolean): Flow<List<Task>> {
        return taskRepository.getTasks(isArchived).map { it.map(TaskMapper::map) }
    }
}
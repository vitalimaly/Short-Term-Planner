package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetArchivedTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun getArchivedTasks(): Flow<List<Task>> {
        return taskRepository.getArchivedTasks()
            .map { it.map(TaskMapper::map) }
            .map { it.sortedBy(Task::dueTo) }
    }
}
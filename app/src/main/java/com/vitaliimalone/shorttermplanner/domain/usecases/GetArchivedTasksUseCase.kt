package com.vitaliimalone.shorttermplanner.domain.usecases

import com.vitaliimalone.shorttermplanner.data.repository.TaskRepository
import com.vitaliimalone.shorttermplanner.domain.mappers.TaskMapper
import com.vitaliimalone.shorttermplanner.domain.models.Task
import kotlinx.coroutines.flow.map

class GetArchivedTasksUseCase(
    private val taskRepository: TaskRepository
) {
    fun getArchivedTasks() = taskRepository.getArchivedTasks()
        .map { it.map(TaskMapper::map) }
        .map { it.sortedBy(Task::dueTo) }
}
package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskEntityMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import org.threeten.bp.OffsetDateTime

class UpdateTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun updateTasks(tasks: List<Task>) {
        tasks.forEach { it.modifiedAt = OffsetDateTime.now() }
        val taskEntities = tasks.map(TaskEntityMapper::map)
        taskRepository.updateTasks(taskEntities)
    }
}
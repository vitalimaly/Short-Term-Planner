package com.vitaliimalone.shorttermplanner.domain.usecases

import com.vitaliimalone.shorttermplanner.data.repository.TaskRepository
import com.vitaliimalone.shorttermplanner.domain.mappers.TaskEntityMapper
import com.vitaliimalone.shorttermplanner.domain.models.Task
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
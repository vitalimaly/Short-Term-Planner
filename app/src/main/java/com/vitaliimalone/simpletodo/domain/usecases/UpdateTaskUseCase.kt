package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskEntityMapper
import com.vitaliimalone.simpletodo.domain.models.Task
import org.threeten.bp.OffsetDateTime

class UpdateTaskUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun updateTask(task: Task) {
        task.modifiedAt = OffsetDateTime.now()
        taskRepository.updateTask(TaskEntityMapper.map(task))
    }
}
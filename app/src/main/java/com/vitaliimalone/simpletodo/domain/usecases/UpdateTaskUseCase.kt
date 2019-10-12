package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskEntityMapper
import com.vitaliimalone.simpletodo.domain.models.Task

class UpdateTaskUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun updateTask(task: Task) {
        taskRepository.updateTask(TaskEntityMapper.map(task))
    }
}
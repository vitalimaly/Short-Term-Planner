package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskEntityMapper
import com.vitaliimalone.simpletodo.domain.models.Task

class DeleteTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun deleteTask(task: Task) {
        taskRepository.deleteTask(TaskEntityMapper.map(task))
    }
}